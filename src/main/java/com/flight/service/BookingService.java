package com.flight.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flight.dto.BookingSummaryDto;
import com.flight.entity.Booking;
import com.flight.entity.Flight;
import com.flight.entity.FlightSchedule;
import com.flight.entity.PassengerDetails;
import com.flight.entity.User;
import com.flight.enums.BookingStatus;
import com.flight.exception.CancellationNotAllowedException;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.FlightScheduleNotFoundException;
import com.flight.exception.InSufficientSeatsException;
import com.flight.exception.PassengerDetailsNotFoundException;
import com.flight.exception.UserNotFoundException;
import com.flight.projections.BookingSummaryProjection;
import com.flight.repo.BookingRepository;
import com.flight.repo.FlightRepository;
import com.flight.repo.FlightScheduleRepository;
import com.flight.repo.PassengerDetailsRepository;
import com.flight.repo.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FlightScheduleRepository flightScheduleRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AuthService authService;

	@Autowired
	private PassengerDetailsRepository passengerDetailsRepository;

	// Allow Single Thread to Process
	@Transactional
	public Booking createBooking(List<PassengerDetails> passengerDetails, Long flightId, Long flightScheduleId,
			UserDetails userDetails) throws PassengerDetailsNotFoundException, InSufficientSeatsException {

		System.out.println("Request Riceved To BookingService");

		if (passengerDetails == null || passengerDetails.isEmpty())
			throw new PassengerDetailsNotFoundException("Passenger Details are Not Found");

		// fetch User
		User user = fetchUserbyEmail(userDetails.getUsername());
		// fetch Flight
		Flight flight = fetchFlightById(flightId);

		// fetch flight schedule
		FlightSchedule flightSchedule = fetchFlightScheduleById(flightScheduleId);

		int numberOfPassengers = passengerDetails.size();

		// validate avilable seats
		try {
			validate(flightSchedule, numberOfPassengers);
		} catch (InSufficientSeatsException e) {
			e.printStackTrace();
			throw new InSufficientSeatsException("Booking failed due to insufficient seats");
		}

		// update flight seats
		flightSchedule.setAvailableSeats(flightSchedule.getAvailableSeats() - numberOfPassengers);
		flightScheduleRepository.save(flightSchedule);
		// initialize booking
		Booking booking = initBooking(flight, flightSchedule, passengerDetails, user);

		return bookingRepository.save(booking);

	}

	// fetching flight
	public Flight fetchFlightById(Long id) {
		return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("The Flight Not Found "));
	}

	// fetching flight schedule
	public FlightSchedule fetchFlightScheduleById(Long id) {
		return flightScheduleRepository.findById(id)
				.orElseThrow(() -> new FlightScheduleNotFoundException("The Flight Schedule Not Found "));
	}

	// validation of avilable seats
	private void validate(FlightSchedule flightSchedule, int numberOfPassengers) throws InSufficientSeatsException {
		if (flightSchedule.getAvailableSeats() < numberOfPassengers) {
			throw new InSufficientSeatsException("Seats Not Avalable ");
		}
	}

	private Booking initBooking(Flight flight, FlightSchedule flightSchedule, List<PassengerDetails> passengers,
			User user) {
		Booking booking = new Booking();
		booking.setUser(user);
		booking.setFlight(flight);
		booking.setFlightSchedule(flightSchedule);
		booking.setTotalPrice((flight.getPrice()) * passengers.size());
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		booking.setCreatedDate(LocalDateTime.now());

		List<PassengerDetails> list = passengers.stream().map(passenger -> {
			passenger.setBooking(booking);
			passenger.setFirstName(passenger.getFirstName().toUpperCase());
			passenger.setLastName(passenger.getLastName().toUpperCase());
			return passenger;
		}).collect(Collectors.toList());

		booking.setPassengers(list);
		return booking;
	}

	public Booking getBooking(Long bookingId) {
		return bookingRepository.findById(bookingId).orElse(null);
	}

	@Transactional
	public void cancelBooking(Long bookingId, UserDetails userDetails) {
		// Check if the Booking Exists
		Booking booking = getBooking(bookingId);
		FlightSchedule flightSchedule = booking.getFlightSchedule();
		Flight flight = booking.getFlight();
		// Verify the User's Identity
		User user = fetchUserbyEmail(userDetails.getUsername());
		if (booking.getUser().getUserId() != user.getUserId())
			throw new RuntimeException("Illgal booking id mentioned");

		// Check Cancellation Eligibility
		cancellationEligibility(booking, flight, flightSchedule);

		// Update Seat Availability
		flightSchedule.setAvailableSeats(flightSchedule.getAvailableSeats() + updateSeats(booking));

		// Update Booking Status
		booking.setBookingStatus(BookingStatus.CANCELED);

		flightScheduleRepository.save(flightSchedule);
		bookingRepository.save(booking);
	}

	private int updateSeats(Booking booking) {
		int count = passengerDetailsRepository.loadPassengerCount(booking.getBookingId());

		return count;
	}

	private void cancellationEligibility(Booking booking, Flight flight, FlightSchedule flightSchedule) {
		LocalDate today = LocalDate.now();

		LocalDate travelDate = flightSchedule.getTravelDate();

		// If the cancellation is before the travel date, allow it
		if (today.isBefore(travelDate)) {
			return;
		}

		// If today is after the travel date, cancellation is not allowed
		if (today.isAfter(travelDate)) {
			throw new CancellationNotAllowedException("Cancellation is not allowed after the travel date.");
		}

		// If cancellation is on the travel date, check time constraints
		LocalTime cancellationDeadline = flight.getSourceTime().minusHours(6);
		LocalTime now = LocalTime.now();

		if (now.isAfter(cancellationDeadline)) {
			throw new CancellationNotAllowedException("Cancellation is not allowed within 6 hours of departure.");
		}
	}

	public User fetchUserbyEmail(String username) {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User is Not Present With this mail");
		return optionalUser.get();
	}

	public List<BookingSummaryDto> getAllBookings() {
		User user = authService.getAuthenticedUser();

		List<BookingSummaryProjection> bookings = bookingRepository.findBookingSummariesByUserId(user.getUserId());
		List<BookingSummaryDto> list = bookings.stream().map(booking -> new BookingSummaryDto(booking.getBookingId(),
				booking.getBookingStatus(), booking.getTotalPrice())).collect(Collectors.toList());

		return list;

	}
}