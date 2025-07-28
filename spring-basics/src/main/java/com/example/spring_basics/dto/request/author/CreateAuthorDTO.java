package com.example.spring_basics.dto.request.author;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CreateAuthorDTO(
        @NotBlank(message = "O nome do autor é obrigatório") String name,
        @NotNull(message = "A data de nascimento é obrigatória") @Past(message = "A data de nascimento deve ser uma data passada") LocalDate birthday,
        LocalDate dayOfDeath,
        @NotNull(message = "O país de origem é obrigatório") Integer countryId) {

}
