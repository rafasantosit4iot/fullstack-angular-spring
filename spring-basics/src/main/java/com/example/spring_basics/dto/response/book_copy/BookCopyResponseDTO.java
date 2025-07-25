package com.example.spring_basics.dto.response.book_copy;

import java.util.List;
import java.util.UUID;

import com.example.spring_basics.dto.response.book.BookSummaryDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTO;
import com.example.spring_basics.model.enums.CopyStatus;

public record BookCopyResponseDTO(
        UUID id,
        String classificationCode,
        CopyStatus status,
        BookSummaryDTO book,
        List<LoanSummaryDTO> loans
        ) {

}
