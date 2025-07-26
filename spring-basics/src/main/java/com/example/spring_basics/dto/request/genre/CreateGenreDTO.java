package com.example.spring_basics.dto.request.genre;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreDTO(
        @NotBlank(message = "O nome do gênero é obrigatório") String name,
        String code) {

}
