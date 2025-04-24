package com.flight.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flight.entity.PassengerDetails;

public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long>{

	@Query(nativeQuery = true, value = "Select count(*) from passengerDetails where booking_id=:bookingId")
	int loadPassengerCount(Long bookingId);

}
