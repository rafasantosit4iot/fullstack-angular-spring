package com.example.spring_basics.mapper.loan;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.loan.LoanSummaryDTO;
import com.example.spring_basics.model.Loan;

@Component
public class LoanSummaryConverter {
    public LoanSummaryDTO toSummaryDTO(Loan loan) {
        return new LoanSummaryDTO(
                loan.getId(),
                loan.getLoanDate(),
                loan.getStatus());
    }

    public List<LoanSummaryDTO> toSummaryList(Collection<Loan> loans) {
        return loans.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
