package com.example.spring_basics.dto.request.reservation;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateReservationDTO(
        @NotNull(message = "O ID do livro é obrigatório") UUID bookCopyId,
        @NotNull(message = "O ID do usuário é obrigatório") UUID userId) {
}
