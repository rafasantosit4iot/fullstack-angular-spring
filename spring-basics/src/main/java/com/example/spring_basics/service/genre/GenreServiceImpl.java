package com.example.spring_basics.service.genre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<GenreResponseDTO> getGenres(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Genre> genres = genreRepository.findAll(pageable);
        return genreMapper.toResponseDTOList(genres);
    }
}
