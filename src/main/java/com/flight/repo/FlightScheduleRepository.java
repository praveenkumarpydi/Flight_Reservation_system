package com.flight.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.flight.entity.FlightSchedule;

import jakarta.persistence.LockModeType;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long>{

	@Lock(LockModeType.WRITE)
	@Query(value = "SELECT fs FROM FLIGHT_SCHEDULE fs WHERE fs.flight_schedule_id =:id", nativeQuery  = true)
	public Optional<FlightSchedule> findByIdWithLock(Long id);
}
