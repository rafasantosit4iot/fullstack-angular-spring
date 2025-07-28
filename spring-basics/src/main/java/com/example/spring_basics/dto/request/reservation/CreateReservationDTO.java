package com.example.spring_basics.dto.request.reservation;

import java.util.UUID;

import com.example.spring_basics.model.enums.ReservationStatus;

import jakarta.validation.constraints.NotNull;

public record CreateReservationDTO(
    @NotNull(message="O ID do livro é obrigatório") UUID bookCopyId,
    ReservationStatus status,
    @NotNull(message="O ID do xx é obrigatório") UUID userId,
    @NotNull(message="O ID do xx é obrigatório") UUID loanId
) {
    public CreateReservationDTO {
        if (status == null) {
            status = ReservationStatus.ACTIVE;
        }
    }    
}
