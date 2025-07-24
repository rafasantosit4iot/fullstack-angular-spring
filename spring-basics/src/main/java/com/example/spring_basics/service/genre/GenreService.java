package com.example.spring_basics.service.genre;

import java.util.List;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;

public interface GenreService {
    GenreResponseDTO createGenre(CreateGenreDTO createGenreDTO);

    List<GenreResponseDTO> getAllGenres();
}
