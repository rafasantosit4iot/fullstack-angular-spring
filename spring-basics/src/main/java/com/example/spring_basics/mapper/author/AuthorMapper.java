package com.example.spring_basics.mapper.author;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.author.CreateAuthorDTO;
import com.example.spring_basics.dto.response.author.AuthorResponseDTO;
import com.example.spring_basics.dto.response.author.AuthorSummaryDTO;
import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.mapper.MappingCordinator;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Country;

@Component
public class AuthorMapper {

    private final MappingCordinator mappingCordinator;

    public AuthorMapper(MappingCordinator mappingCordinator) {
        this.mappingCordinator = mappingCordinator;
    }

    public Author toEntity(CreateAuthorDTO createAuthorDTO, Country country) {
        Author author = new Author();

        author.setName(createAuthorDTO.name());
        author.setBirthday(createAuthorDTO.birthday());
        author.setDayOfDeath(createAuthorDTO.dayOfDeath());
        author.setOriginCountry(country);

        return author;
    }

    // RESPONSE DTO
    // -------------------------------------------------------------------------------------------

    public AuthorResponseDTO toResponseDTO(Author author) {
        UUID id = author.getId();
        String name = author.getName();
        LocalDate birthday = author.getBirthday();
        LocalDate dayOfDeath = author.getDayOfDeath();
        CountrySummaryDTO country = mappingCordinator.toCountrySummary(author.getOriginCountry());
        List<BookSummaryDTO> books = mappingCordinator.toBookSummaryList(author.getBooks());

        // DTO
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO(id, name, birthday, dayOfDeath, country, books);
        return authorResponseDTO;
    }

    public List<AuthorResponseDTO> toResponseDTOList(List<Author> authors) {
        return authors.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // SUMMARY DTO
    // -------------------------------------------------------------------------------------------

    public AuthorSummaryDTO toAuthorSummaryDTO(Author author) {
        UUID id = author.getId();
        String name = author.getName();
        Integer countryId = author.getOriginCountry().getId();

        // DTO
        AuthorSummaryDTO authorSummaryDTO = new AuthorSummaryDTO(id, name, countryId);
        return authorSummaryDTO;
    }
}
