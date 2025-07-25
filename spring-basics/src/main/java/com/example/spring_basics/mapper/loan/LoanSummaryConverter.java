package com.example.spring_basics.mapper.loan;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.loan.LoanSummaryDTO;
import com.example.spring_basics.model.Loan;

@Component
public class LoanSummaryConverter {
    public LoanSummaryDTO toSummaryDTO(Loan loan) {
        LoanSummaryDTO loanSummaryDTO = new LoanSummaryDTO(
            
        );
    }
}
