package com.example.spring_basics.dto.response.book;

import java.util.UUID;

public record AuthorSummaryDTO(UUID authorId, String name, Integer countryId) {
}
