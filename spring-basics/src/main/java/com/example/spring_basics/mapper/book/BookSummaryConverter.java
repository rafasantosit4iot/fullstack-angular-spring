package com.example.spring_basics.mapper.book;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.model.Book;

@Component
public class BookSummaryConverter {
    public BookSummaryDTO toSummaryDTO(Book book) {
        return new BookSummaryDTO(
                book.getId(),
                book.getTitle(),
                book.getEditionNumber(),
                book.getYearOfRelease(),
                book.getIsbnCode());
    }

    public List<BookSummaryDTO> toSummaryList(Collection<Book> books) {
        return books.stream()
            .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
