package com.example.spring_basics.dto.response.genre;

import java.util.List;

import com.example.spring_basics.dto.response.book.BookSummaryDTO;

public record GenreResponseDTO(
                Integer id,
                String name,
                String code,
                List<BookSummaryDTO> books) {
}
