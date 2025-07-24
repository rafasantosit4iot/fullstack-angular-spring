package com.example.spring_basics.service.genre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;
import com.example.spring_basics.mapper.genre.GenreMapper;
import com.example.spring_basics.model.Genre;
import com.example.spring_basics.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

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
