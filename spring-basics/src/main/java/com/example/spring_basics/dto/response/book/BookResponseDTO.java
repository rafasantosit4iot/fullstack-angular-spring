package com.example.spring_basics.dto.response.book;

import java.util.UUID;

import com.example.spring_basics.dto.response.author.AuthorSummaryDTO;
import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;

public record BookResponseDTO(
        UUID id,
        String title,
        Integer editionNumber,
        String synopsis,
        String isbnCode,
        Integer yearOfRelease,
        AuthorSummaryDTO authorSummaryDTO,
        PublisherSummaryDTO publisherSummaryDTO) {

}
