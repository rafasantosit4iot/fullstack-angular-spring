package com.example.spring_basics.service.loan;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.spring_basics.model.Loan;

@Service
public class LoanCalculator {
    public LocalDate calculateDueDate(LocalDate loanDate) {
        LocalDate dueDate = LocalDate.now();
        dueDate.plusDays(15);
        return dueDate;
    }
    
    public double calculateFineAmount(Loan loan) {
        return 2.00;
    }
}
