package com.example.spring_basics.dto.response.loan;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.model.enums.LoanStatus;

public record LoanResponseDTO(
        UUID id,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        Integer fineAmount,
        LoanStatus status,
        BookCopySummaryDTO bookCopy,
        UserSummaryDTO user) {

}
