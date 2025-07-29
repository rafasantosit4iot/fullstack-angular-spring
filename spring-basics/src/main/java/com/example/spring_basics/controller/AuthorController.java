package com.example.spring_basics.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;
import com.example.spring_basics.service.author.AuthorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<AuthorResponseDTO>> getAuthors(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<AuthorResponseDTO> allAuthors = authorService.getAuthors(pageNumber, pageSize);
        return ResponseEntity.ok(allAuthors);
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> addAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO) {
        AuthorResponseDTO responseAuthor = authorService.createAuthor(createAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        this.authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }
}
