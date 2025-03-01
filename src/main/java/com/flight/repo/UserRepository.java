package com.flight.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
