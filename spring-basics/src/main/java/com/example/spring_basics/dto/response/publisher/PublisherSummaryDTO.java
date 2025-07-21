package com.example.spring_basics.dto.response.publisher;

import java.util.UUID;

public record PublisherSummaryDTO(
        UUID id,
        String name,
        String founder,
        Integer foundationYear) {

}
