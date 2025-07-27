package com.example.spring_basics.dto.request.reservation;

import java.util.UUID;

public record CreateReservationDTO(
    UUID bookId,
    UUID userId,
    UUID loanId
) {
    
}
