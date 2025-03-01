package com.flight.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
}

