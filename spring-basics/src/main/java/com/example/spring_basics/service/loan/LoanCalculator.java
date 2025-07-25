package com.example.spring_basics.service.loan;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class LoanCalculator {
    public LocalDate calculateDueDate(LocalDate loanDate){
        LocalDate dueDate = LocalDate.now();
        dueDate.plusDays(15);
        return dueDate;
    }
}
