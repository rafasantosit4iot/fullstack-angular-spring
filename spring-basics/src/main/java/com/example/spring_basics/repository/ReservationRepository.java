package com.example.spring_basics.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.enums.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Long countByUserIdAndStatus(UUID userId, ReservationStatus status);

    List<Reservation> findByStatusAndExpirationDateBefore(
            ReservationStatus status,
            LocalDate expirationDate);
}
