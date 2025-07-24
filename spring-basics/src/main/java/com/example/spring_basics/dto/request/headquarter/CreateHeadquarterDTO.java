package com.example.spring_basics.dto.request.headquarter;


import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateHeadquarterDTO(
        @NotBlank(message="O nome da cidade da sede é obrigatório") String city,
        String state,
        @NotNull(message="O ID do país da sede é obrigatório") Integer countryId,
        String street,
        @Max(value=99999, message="Valor acima do permitido") Integer number,
        String zipCode,
        @NotNull(message="ID da editora é obrigatório") UUID publisherId) {

}
