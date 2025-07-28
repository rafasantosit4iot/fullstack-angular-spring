package com.example.spring_basics.dto.request.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
                @NotBlank(message = "O nome do usuário é obrigatório") String name,
                @NotBlank(message = "O email do usuário é obrigatório") String email,
                @Max(value=10, message="Um usuário pode ter no máximo 10 empréstimos ao mesmo tempo")Integer maxActiveLoans,
                Boolean active) {

        public CreateUserDTO {
                if (maxActiveLoans == null) {
                        maxActiveLoans = 2;
                }
                if (active == null) {
                        active = true;
                }
        }
}
