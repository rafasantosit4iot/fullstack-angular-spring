package com.example.spring_basics.mapper.book_copy;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.book_copy.CreateBookCopyDTO;
import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopyResponseDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTOToCopy;
import com.example.spring_basics.mapper.book.BookSummaryConverter;
import com.example.spring_basics.mapper.loan.LoanSummaryConverter;
import com.example.spring_basics.model.Book;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.enums.CopyStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookCopyMapper {

    private final BookSummaryConverter bookSummaryConverter;
    private final LoanSummaryConverter loanSummaryConverter;

    public BookCopy toEntity(CreateBookCopyDTO createBookCopyDTO, Book book, String classificationCode,
            CopyStatus status) {
        BookCopy bookCopy = new BookCopy();

        bookCopy.setClassificationCode(classificationCode);
        bookCopy.setStatus(status);
        bookCopy.setBook(book);

        return bookCopy;
    }

    public BookCopyResponseDTO toResponseDTO(BookCopy bookCopy) {
        UUID id = bookCopy.getId();
        String classificationCode = bookCopy.getClassificationCode();
        CopyStatus status = bookCopy.getStatus();
        BookSummaryDTO bookSummaryDTO = bookSummaryConverter.toSummaryDTO(bookCopy.getBook());
        List<LoanSummaryDTOToCopy> loans = loanSummaryConverter.toBookCopySummaryList(bookCopy.getLoans());

        BookCopyResponseDTO bookCopyResponseDTO = new BookCopyResponseDTO(id, classificationCode, status,
                bookSummaryDTO, loans);
        return bookCopyResponseDTO;
    }

    public Page<BookCopyResponseDTO> toResponseDTOList(Page<BookCopy> bookCopies) {
        return bookCopies.map(this::toResponseDTO);
    }
}
