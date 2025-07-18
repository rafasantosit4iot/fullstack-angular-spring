package com.example.spring_basics.dto.request.country;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CreateCountryDTO(
        @NotBlank(message = "O nome do país é obrigatório") String name,
        @Length(min = 3, max = 3, message = "O código de ISO deve ter 2 caracteres") String isoCode) {

}
