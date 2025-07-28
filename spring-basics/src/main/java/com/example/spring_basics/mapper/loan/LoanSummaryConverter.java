package com.example.spring_basics.mapper.loan;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.loan.LoanSummaryDTOToCopy;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTOToUser;
import com.example.spring_basics.model.Loan;

@Component
public class LoanSummaryConverter {

    // BOOK COPY
    public LoanSummaryDTOToCopy toSummaryDTOToCopy(Loan loan) {
        LoanSummaryDTOToCopy loanSummaryDTO = new LoanSummaryDTOToCopy(
                loan.getId(),
                loan.getUser().getName(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getStatus());

        return loanSummaryDTO;
    }

    public List<LoanSummaryDTOToCopy> toBookCopySummaryList(Collection<Loan> loans) {
        return loans.stream()
                .map(this::toSummaryDTOToCopy)
                .collect(Collectors.toList());
    }

    // USER
    public LoanSummaryDTOToUser toSummaryDTOToUser(Loan loan) {
        LoanSummaryDTOToUser loanSummaryDTO = new LoanSummaryDTOToUser(
                loan.getId(),
                loan.getBookCopy().getClassificationCode(),
                loan.getBookCopy().getBook().getTitle(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getFineAmount(),
                loan.getStatus());

        return loanSummaryDTO;
    }

    public List<LoanSummaryDTOToUser> toUserSummaryList(Collection<Loan> loans) {
        return loans.stream()
                .map(this::toSummaryDTOToUser)
                .collect(Collectors.toList());
    }

}
