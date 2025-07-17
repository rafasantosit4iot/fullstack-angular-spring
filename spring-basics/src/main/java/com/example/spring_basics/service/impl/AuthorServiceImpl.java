package com.example.spring_basics.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;
import com.example.spring_basics.mapper.AuthorMapper;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.repository.AuthorRepository;
import com.example.spring_basics.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    
    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDTO createAuthor(CreateAuthorDTO createAuthorDTO) {
        Author author = authorMapper.toEntity(createAuthorDTO);
        author = authorRepository.save(author);
        return authorMapper.toResponseDTO(author);
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toResponseDTOList(authors);
    }

    @Override
    public void deleteAuthor(UUID authorId) {
        authorRepository.deleteById(authorId);
    }
}
