package com.example.spring_basics.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring_basics.model.enums.LoanStatus;
import com.example.spring_basics.service.loan.LoanCalculator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "loans")
public class Loan {

    @Autowired
    private LoanCalculator loanCalculator;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer fineAmount;
    private LoanStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Loan(BookCopy bookCopy, User user) {
        this.bookCopy = bookCopy;
        this.user = user;
        this.dueDate = loanCalculator.calculateDueDate(loanDate);
        this.loanDate = LocalDate.now();
        this.status = LoanStatus.ACTIVE;
    }

    
}
