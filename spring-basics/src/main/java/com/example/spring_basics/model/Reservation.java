package com.example.spring_basics.model;

import java.time.LocalDate;
import java.util.UUID;

import com.example.spring_basics.model.enums.ReservationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    private LocalDate reservationDate;
    private LocalDate expirationDate;
    private ReservationStatus status;

    @OneToOne(mappedBy="reservation", cascade=CascadeType.ALL)
    private Loan loan;

}
