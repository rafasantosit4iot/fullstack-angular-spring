package com.example.spring_basics.dto.request.book_copy;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateBookCopyDTO(
                @NotNull(message = "O ID da cópia do livro é obrigatório") UUID bookId) {

}
