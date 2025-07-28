package com.example.spring_basics.service.genre;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;
import com.example.spring_basics.mapper.genre.GenreMapper;
import com.example.spring_basics.model.Genre;
import com.example.spring_basics.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreResponseDTO createGenre(CreateGenreDTO createGenreDTO) {
        Genre genre = genreMapper.toEntity(createGenreDTO);
        genre = genreRepository.save(genre);
        return genreMapper.toResponseDTO(genre);
    }

    @Override
    public List<GenreResponseDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toResponseDTOList(genres);
    }
}
