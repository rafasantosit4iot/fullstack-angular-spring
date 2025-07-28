package com.example.spring_basics.dto.response.reservation;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.model.enums.ReservationStatus;

public record ReservationSummaryDTO(
        UUID id,
        String bookCopyTitle,
        String userName,
        LocalDate reservationDate,
        LocalDate expirationDate,
        ReservationStatus status,
        UUID loanId) {

}
