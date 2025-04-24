package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.flight.entity.Booking;
import com.flight.entity.PassengerDetails;
import com.flight.entity.User;
import com.flight.exception.InSufficientSeatsException;
import com.flight.exception.PassengerDetailsNotFoundException;
import com.flight.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public Booking createBooking(@RequestBody List<PassengerDetails> passengerDetails, @RequestParam Long flightId, @RequestParam Long flightScheduleId,@AuthenticationPrincipal UserDetails userDetails) throws PassengerDetailsNotFoundException, InSufficientSeatsException {
        return bookingService.createBooking(passengerDetails,flightId,flightScheduleId,userDetails);
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id);
    }

    @PutMapping("/cancel/{id}")
    public void cancelBooking(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails) {
        bookingService.cancelBooking(id,userDetails);
    }
}