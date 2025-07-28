package com.example.spring_basics.mapper.book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.author.AuthorSummaryDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.genre.GenreSummaryDTO;
import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;
import com.example.spring_basics.mapper.author.AuthorSummaryConverter;
import com.example.spring_basics.mapper.book_copy.BookCopySummaryConverter;
import com.example.spring_basics.mapper.genre.GenreSummaryConverter;
import com.example.spring_basics.mapper.publisher.PublisherSummaryConverter;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Book;
import com.example.spring_basics.model.Genre;
import com.example.spring_basics.model.Publisher;

@Component
public class BookMapper {

    @Autowired
    AuthorSummaryConverter authorSummaryConverter;
    PublisherSummaryConverter publisherSummaryConverter;
    GenreSummaryConverter genreSummaryConverter;
    BookCopySummaryConverter bookCopySummaryConverter;

    public Book toEntity(CreateBookDTO createBookDTO, Author author, Publisher publisher, Genre genre) {
        Book book = new Book();

        book.setTitle(createBookDTO.title());
        book.setEditionNumber(createBookDTO.editionNumber());
        book.setSynopsis(createBookDTO.synopsis());
        book.setIsbnCode(createBookDTO.isbnCode());
        book.setYearOfRelease(createBookDTO.yearOfRelease());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genre);

        return book;
    }

    public BookResponseDTO toResponseDTO(Book book) {
        UUID id = book.getId();
        String title = book.getTitle();
        Integer editionNumber = book.getEditionNumber();
        String synopsis = book.getSynopsis();
        String isbnCode = book.getIsbnCode();
        Integer yearOfRelease = book.getYearOfRelease();

        GenreSummaryDTO genre = genreSummaryConverter.toSummaryDTO(book.getGenre());
        AuthorSummaryDTO authorSummaryDTO = authorSummaryConverter.toSummaryDTO(book.getAuthor());
        PublisherSummaryDTO publisherSummaryDTO = publisherSummaryConverter.toSummaryDTO(book.getPublisher());
        List<BookCopySummaryDTO> copies = bookCopySummaryConverter.toSummarylList(book.getCopies());

        // DTO
        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                id,
                title,
                editionNumber,
                synopsis,
                isbnCode,
                yearOfRelease,
                genre,
                authorSummaryDTO,
                publisherSummaryDTO,
                copies);
        return bookResponseDTO;
    }

    public List<BookResponseDTO> toResponseListDTO(List<Book> books) {
        return books.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
