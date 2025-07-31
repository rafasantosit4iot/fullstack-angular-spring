package com.example.spring_basics.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.enums.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, UUID>{
    List<Loan> findByStatusAndDueDateBefore(
        LoanStatus status, 
        LocalDate dueDate
    );    
}
