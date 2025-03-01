package com.flight.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.PassengerDetails;

public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long>{

}
