package com.example.spring_basics.dto.response.author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDTO(UUID id, String name, LocalDate birthday) {
}
