package com.flight.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.Booking;
import com.flight.entity.User;
import com.flight.projections.BookingSummaryProjection;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findAllByUser(User user);

	@Query("SELECT b.bookingId AS bookingId, b.bookingStatus AS bookingStatus, b.totalPrice AS totalPrice FROM Booking b WHERE b.user.userId = :userId")
	List<BookingSummaryProjection> findBookingSummariesByUserId(@Param("userId") Long userId);

}
