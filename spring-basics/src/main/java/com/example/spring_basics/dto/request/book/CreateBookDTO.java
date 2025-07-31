package com.example.spring_basics.dto.request.book;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookDTO(
        @NotBlank(message = "O título do livro é obrigatório") @Length(max = 150, message = "O título do livro deve conter no máximo 150 caracteres") String title,
        @Min(value = 1, message = "A edição deve ser maior ou igual a 1") Integer editionNumber,
        String synopsis,
        @Length(min = 10, max = 13, message = "O código ISBN deve conter 10 ou 13 caracteres") String isbnCode,
        @NotNull(message = "O ano de lançamento do livro é obrigatório") Integer yearOfRelease,
        @NotNull(message = "O ID do autor é obrigatório") UUID authorId,
        @NotNull(message = "O ID da editora é obrigatório") UUID publisherId,
        @NotNull(message = "O ID do gênero é obrigatório") Integer genreId) {

}
