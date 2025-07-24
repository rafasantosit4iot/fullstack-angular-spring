package com.example.spring_basics.service.author;

import java.util.List;
import java.util.UUID;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;

public interface AuthorService {
    AuthorResponseDTO createAuthor(CreateAuthorDTO createAuthorDTO);

    List<AuthorResponseDTO> getAllAuthors();

    void deleteAuthor(UUID authorId);
}
