package com.example.spring_basics.handlers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.enums.LoanStatus;
import com.example.spring_basics.model.enums.ReservationStatus;
import com.example.spring_basics.repository.LoanRepository;
import com.example.spring_basics.repository.ReservationRepository;
import com.example.spring_basics.service.book_copy.CopyValidator;
import com.example.spring_basics.service.loan.LoanCalculator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpirationScheduler {
     private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final CopyValidator copyValidator;
    private final LoanCalculator loanCalculator;

    // Verifica empréstimos vencidos diariamente às 2:00 AM
    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void checkOverdueLoans() {
        List<Loan> overdueLoans = loanRepository.findByStatusAndDueDateBefore(
            LoanStatus.ACTIVE, 
            LocalDate.now()
        );
        
        overdueLoans.forEach(loan -> {
            loan.setStatus(LoanStatus.OVERDUE);
            loan.setFineAmount(loanCalculator.calculateFineAmount(loan)); // Ex: 1 unid./dia
            loanRepository.save(loan);
        });
    }

    // Verifica reservas expiradas a cada 30 minutos
    @Scheduled(fixedRate = 30 * 60 * 1000)
    @Transactional
    public void checkExpiredReservations() {
        List<Reservation> expiredReservations = reservationRepository
            .findByStatusAndExpirationDateBefore(
                ReservationStatus.ACTIVE, 
                LocalDate.now()
            );
        
        expiredReservations.forEach(reservation -> {
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
            copyValidator.markBookCopyAsAvailable(reservation.getBookCopy());
        });
    }
}
