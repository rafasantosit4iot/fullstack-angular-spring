package com.example.spring_basics.dto.response.loan;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.model.enums.LoanStatus;

public record LoanSummaryDTOToUser(
                UUID id,
                String copyClassificationCode,
                String bookCopyTitle,
                LocalDate loanDate,
                LocalDate dueDate,
                double fineAmount,
                LoanStatus status) {

}
