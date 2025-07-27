package com.example.spring_basics.dto.response.reservation;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTO;
import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.model.enums.ReservationStatus;

public record ReservationResponseDTO(
        UUID id,
        BookCopySummaryDTO bookCopy,
        UserSummaryDTO user,
        LocalDate reservationDate,
        LocalDate expirationDate,
        ReservationStatus status,
        LoanSummaryDTO loan
        ) {

}
