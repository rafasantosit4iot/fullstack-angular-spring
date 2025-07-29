package com.example.spring_basics.mapper.genre;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;
import com.example.spring_basics.mapper.book.BookSummaryConverter;
import com.example.spring_basics.model.Genre;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GenreMapper {

    private final BookSummaryConverter bookSummaryConverter;

    public Genre toEntity(CreateGenreDTO createGenreDTO) {
        Genre genre = new Genre();

        genre.setName(createGenreDTO.name());
        genre.setCode(createGenreDTO.code());

        return genre;
    }

    public GenreResponseDTO toResponseDTO(Genre genre) {
        Integer id = genre.getId();
        String name = genre.getName();
        String code = genre.getCode();
        List<BookSummaryDTO> books = bookSummaryConverter.toSummaryList(genre.getBooks());

        // DTO
        GenreResponseDTO genreResponseDTO = new GenreResponseDTO(id, name, code, books);
        return genreResponseDTO;
    }

    public Page<GenreResponseDTO> toResponseDTOList(Page<Genre> genres) {
        return genres.map(this::toResponseDTO);
    }
}
