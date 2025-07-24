package com.example.spring_basics.service.author;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;
import com.example.spring_basics.mapper.author.AuthorMapper;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Country;
import com.example.spring_basics.repository.AuthorRepository;
import com.example.spring_basics.repository.CountryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    
    private final AuthorMapper authorMapper;
    
    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public AuthorResponseDTO createAuthor(CreateAuthorDTO createAuthorDTO) {
        Country country = countryRepository.findById(createAuthorDTO.countryId())
            .orElseThrow(() -> new EntityNotFoundException("País não encontrado"));
        Author author = authorMapper.toEntity(createAuthorDTO, country);
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
