package com.example.spring_basics.dto.response.loan;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.model.enums.LoanStatus;

public record LoanSummaryDTOToCopy(
                UUID id,
                String userName,
                LocalDate loanDate,
                LocalDate dueDate,
                LoanStatus status) {

}
