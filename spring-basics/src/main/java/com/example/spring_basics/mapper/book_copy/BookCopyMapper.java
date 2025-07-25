package com.example.spring_basics.mapper.book_copy;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.book_copy.CreateBookCopyDTO;
import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopyResponseDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTO;
import com.example.spring_basics.mapper.book.BookSummaryConverter;
import com.example.spring_basics.mapper.loan.LoanSummaryConverter;
import com.example.spring_basics.model.Book;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.enums.CopyStatus;

@Component
public class BookCopyMapper {
    @Autowired
    BookSummaryConverter bookSummaryConverter;

    @Autowired
    LoanSummaryConverter loanSummaryConverter;

    public BookCopy toEntity(CreateBookCopyDTO createBookCopyDTO, Book book, String classificationCode) {
        BookCopy bookCopy = new BookCopy();

        bookCopy.setClassificationCode(classificationCode);
        bookCopy.setStatus(createBookCopyDTO.status());
        bookCopy.setBook(book);

        return bookCopy;
    }

    public BookCopyResponseDTO toResponseDTO(BookCopy bookCopy) {
        UUID id = bookCopy.getId();
        String classificationCode = bookCopy.getClassificationCode();
        CopyStatus status = bookCopy.getStatus();
        BookSummaryDTO bookSummaryDTO = bookSummaryConverter.toSummaryDTO(bookCopy.getBook());

        List<LoanSummaryDTO> loans = loanSummaryConverter.toSummaryList(bookCopy.getLoans());

        return new BookCopyResponseDTO(id, classificationCode, status, bookSummaryDTO, loans);

    }
}
