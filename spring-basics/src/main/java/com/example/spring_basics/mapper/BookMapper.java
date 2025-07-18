package com.example.spring_basics.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.book.AuthorSummaryDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Book;

@Component
public class BookMapper {

    public Book toEntity(CreateBookDTO createBookDTO, Author author) {
        Book book = new Book();

        book.setTitle(createBookDTO.title());
        book.setEditionNumber(createBookDTO.editionNumber());
        book.setSynopsis(createBookDTO.synopsis());
        book.setIsbnCode(createBookDTO.isbnCode());
        book.setYearOfRelease(createBookDTO.yearOfRelease());
        book.setAuthor(author);

        return book;
    }

    public BookResponseDTO toResponseDTO(Book book) {
        UUID id = book.getId();
        String title = book.getTitle();
        Integer editionNumber = book.getEditionNumber();
        String synopsis = book.getSynopsis();
        String isbnCode = book.getIsbnCode();
        Integer yearOfRelease = book.getYearOfRelease();
        AuthorSummaryDTO authorSummaryDTO = toAuthorSummaryDTO(book.getAuthor());

        // DTO
        BookResponseDTO bookResponseDTO = new BookResponseDTO(id, title, editionNumber, synopsis, isbnCode,
                yearOfRelease, authorSummaryDTO);
        return bookResponseDTO;
    }

    public List<BookResponseDTO> toResponseListDTO(List<Book> books) {
        return books.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private AuthorSummaryDTO toAuthorSummaryDTO(Author author) {
        UUID id = author.getId();
        String name = author.getName();
        Integer countryId = author.getOriginCountry().getId();

        // DTO
        AuthorSummaryDTO authorSummaryDTO = new AuthorSummaryDTO(id, name, countryId);
        return authorSummaryDTO;
    }

}
