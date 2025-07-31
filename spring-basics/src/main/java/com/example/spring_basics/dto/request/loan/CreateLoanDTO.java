package com.example.spring_basics.dto.request.loan;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateLoanDTO(
    @NotNull(message="O ID da cópia do livro é obrigatório") UUID bookCopyId,
    @NotNull(message="O ID do usuário é obrigatório") UUID userId,
    UUID reservationId
) {
    
}
