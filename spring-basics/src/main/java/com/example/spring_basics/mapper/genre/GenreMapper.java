package com.example.spring_basics.mapper.genre;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;
import com.example.spring_basics.model.Genre;

@Component
public class GenreMapper {
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

        // DTO
        GenreResponseDTO genreResponseDTO = new GenreResponseDTO(id, name, code);
        return genreResponseDTO;
    }

    public List<GenreResponseDTO> toResponseDTOList(List<Genre> genres) {
        return genres.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
