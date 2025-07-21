package com.example.spring_basics.dto.response.author;

import java.util.UUID;

public record AuthorSummaryDTO(UUID authorId, String name, Integer countryId) {
}
