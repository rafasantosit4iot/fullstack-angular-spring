package com.example.spring_basics.mapper.book_copy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.model.BookCopy;

@Component
public class BookCopySummaryConverter {

    public BookCopySummaryDTO toSummaryDTO(BookCopy bookCopy) {
        return new BookCopySummaryDTO(
                bookCopy.getId(),
                bookCopy.getClassificationCode(),
                bookCopy.getBook().getTitle(),
                bookCopy.getStatus());
    }

    public List<BookCopySummaryDTO> toSummarylList(Collection<BookCopy> copies) {
        return copies.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
