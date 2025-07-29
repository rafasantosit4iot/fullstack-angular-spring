package com.example.spring_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.request.genre.CreateGenreDTO;
import com.example.spring_basics.dto.response.genre.GenreResponseDTO;
import com.example.spring_basics.service.genre.GenreService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<Page<GenreResponseDTO>> getGenres(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<GenreResponseDTO> genres = genreService.getGenres(pageNumber, pageSize);
        return ResponseEntity.ok(genres);
    }

    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@Valid @RequestBody CreateGenreDTO createGenreDTO) {
        GenreResponseDTO genreResponseDTO = genreService.createGenre(createGenreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreResponseDTO);
    }
}
