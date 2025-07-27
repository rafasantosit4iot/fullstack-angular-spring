package com.example.spring_basics.service.reservation;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class ReservationCalculator {
    public LocalDate calculateExpirationDate(LocalDate baseDate) {
        return LocalDate.now();
    }
}
