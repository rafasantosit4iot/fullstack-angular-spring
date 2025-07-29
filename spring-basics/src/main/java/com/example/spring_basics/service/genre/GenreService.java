package com.example.spring_basics.service.genre;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;

public interface GenreService {
    GenreResponseDTO createGenre(CreateGenreDTO createGenreDTO);

    Page<GenreResponseDTO> getGenres(int pageNumber, int pageSize);
}
