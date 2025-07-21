package com.example.spring_basics.dto.response.publisher;

import java.util.List;
import java.util.UUID;

import com.example.spring_basics.dto.response.headquarter.HeadquarterSummaryDTO;

public record PublisherResponseDTO(
                UUID id,
                String name,
                String founder,
                Integer foundationYear,
                List<HeadquarterSummaryDTO> headquarters) {
}
