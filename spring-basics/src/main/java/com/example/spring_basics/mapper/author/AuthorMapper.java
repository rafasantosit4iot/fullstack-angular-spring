package com.example.spring_basics.mapper.author;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;
import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.mapper.book.BookSummaryConverter;
import com.example.spring_basics.mapper.country.CountrySummaryConverter;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Country;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorMapper {

    private final BookSummaryConverter bookSummaryConverter;
    private final CountrySummaryConverter countrySummaryConverter;

    public Author toEntity(CreateAuthorDTO createAuthorDTO, Country country) {
        Author author = new Author();

        author.setName(createAuthorDTO.name());
        author.setBirthday(createAuthorDTO.birthday());
        author.setDayOfDeath(createAuthorDTO.dayOfDeath());
        author.setOriginCountry(country);

        return author;
    }

    public AuthorResponseDTO toResponseDTO(Author author) {
        UUID id = author.getId();
        String name = author.getName();
        LocalDate birthday = author.getBirthday();
        LocalDate dayOfDeath = author.getDayOfDeath();
        CountrySummaryDTO country = countrySummaryConverter.toSummaryDTO(author.getOriginCountry());
        List<BookSummaryDTO> books = bookSummaryConverter.toSummaryList(author.getBooks());

        // DTO
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO(id, name, birthday, dayOfDeath, country, books);
        return authorResponseDTO;
    }

    public Page<AuthorResponseDTO> toResponseDTOList(Page<Author> authors) {
        return authors.map(this::toResponseDTO);
    }
}
