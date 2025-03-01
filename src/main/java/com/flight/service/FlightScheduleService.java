package com.flight.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entity.Flight;
import com.flight.entity.FlightSchedule;
import com.flight.repo.FlightRepository;
import com.flight.repo.FlightScheduleRepository;

@Service
public class FlightScheduleService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public void createSchedulesForFlight(Long flightId, int days) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        LocalDate today = LocalDate.now();

        for (int i = 0; i < days; i++) {
            FlightSchedule schedule = new FlightSchedule();
            schedule.setFlight(flight);
            schedule.setTravelDate(today.plusDays(i));
            schedule.setAvailableSeats(flight.getTotalSeats());
            flightScheduleRepository.save(schedule);
        }
    }
}
