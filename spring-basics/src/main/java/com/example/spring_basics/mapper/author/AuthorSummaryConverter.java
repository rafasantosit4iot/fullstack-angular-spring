package com.example.spring_basics.mapper.author;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.author.AuthorSummaryDTO;
import com.example.spring_basics.model.Author;

@Component
public class AuthorSummaryConverter {

    public AuthorSummaryDTO toSummaryDTO(Author author) {
        return new AuthorSummaryDTO(
                author.getId(),
                author.getName(),
                author.getOriginCountry().getName());
    }

    public List<AuthorSummaryDTO> toSummaryList(Collection<Author> authors) {
        return authors.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
