package com.example.spring_basics.dto.response.book;

import java.util.UUID;

public record BookSummaryDTO(
        UUID id,
        String title,
        Integer editionNumber,
        Integer yearOfRelease,
        String isbnCode,
        String genreName) {

}
