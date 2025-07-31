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
import jakarta.persistence.OneToOne;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fineAmount;
    private LoanStatus status;

    @ManyToOne  
    @JoinColumn(nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;
}
