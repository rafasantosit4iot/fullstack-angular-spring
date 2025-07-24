package com.example.spring_basics.dto.response.author;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.country.CountrySummaryDTO;

public record AuthorResponseDTO(
        UUID id,
        String name,
        LocalDate birthday,
        LocalDate dayOfDeath,
        CountrySummaryDTO country,
        List<BookSummaryDTO> books) {

}
