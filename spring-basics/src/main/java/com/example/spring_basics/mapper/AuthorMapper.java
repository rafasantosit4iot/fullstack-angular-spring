package com.example.spring_basics.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;
import com.example.spring_basics.model.Author;

@Component
public class AuthorMapper {
    public Author toEntity(CreateAuthorDTO createAuthorDTO) {
        Author author = new Author();

        author.setName(createAuthorDTO.name());
        author.setBirthday(createAuthorDTO.birthday());

        return author;
    }

    public AuthorResponseDTO toResponseDTO(Author author) {
        UUID id = author.getId();
        String name = author.getName();
        LocalDate birthday = author.getBirthday();

        // DTO
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO(id, name, birthday);
        return authorResponseDTO;
    }

    public List<AuthorResponseDTO> toResponseDTOList(List<Author> authors) {
        return authors.stream()
            .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
