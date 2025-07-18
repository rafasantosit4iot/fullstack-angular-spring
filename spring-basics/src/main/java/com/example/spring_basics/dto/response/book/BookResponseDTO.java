package com.example.spring_basics.dto.response.book;

import java.util.UUID;

public record BookResponseDTO(UUID id, String title, Integer editionNumber, String synopsis, String isbnCode, Integer yearOfRelease, AuthorSummaryDTO authorSummaryDTO) {
    
}
