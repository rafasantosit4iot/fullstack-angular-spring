package com.example.spring_basics.service.author;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;

public interface AuthorService {
    AuthorResponseDTO createAuthor(CreateAuthorDTO createAuthorDTO);

    Page<AuthorResponseDTO> getAuthors(int pageNumber, int pageSize);

    void deleteAuthor(UUID authorId);
}
