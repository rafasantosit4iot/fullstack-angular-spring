package com.example.spring_basics.mapper.genre;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.genre.GenreSummaryDTO;
import com.example.spring_basics.model.Genre;

@Component
public class GenreSummaryConverter {
    public GenreSummaryDTO toSummaryDTO(Genre genre) {
        return new GenreSummaryDTO(
                genre.getId(),
                genre.getName());
    }
}
