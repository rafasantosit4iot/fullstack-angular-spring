package com.example.spring_basics.model;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.model.enums.LoanStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer fineAmount;
    private LoanStatus status;
}
