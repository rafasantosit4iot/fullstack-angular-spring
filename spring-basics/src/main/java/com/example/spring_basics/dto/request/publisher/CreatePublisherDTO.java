package com.example.spring_basics.dto.request.publisher;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePublisherDTO(
        @NotBlank(message="O nome da editora é obrigatório") @Length(max=100, message="O nome da editora deve ter no máximo 100 caracteres") String name,
        @NotBlank(message="O nome do fundador é obrigatório") @Length(max=100, message="O nome do fundador deve ter no máximo 100 caracteres") String founder,
        @NotNull(message="O ano de fundação é brigatório") Integer foundationYear) {

}
