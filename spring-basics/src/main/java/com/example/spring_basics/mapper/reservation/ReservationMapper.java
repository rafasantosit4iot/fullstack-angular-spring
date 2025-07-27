package com.example.spring_basics.mapper.reservation;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.reservation.CreateReservationDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTO;
import com.example.spring_basics.dto.response.reservation.ReservationResponseDTO;
import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.mapper.book_copy.BookCopySummaryConverter;
import com.example.spring_basics.mapper.loan.LoanSummaryConverter;
import com.example.spring_basics.mapper.user.UserSummaryConverter;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.ReservationStatus;

@Component
public class ReservationMapper {

    @Autowired
    BookCopySummaryConverter bookCopySummaryConverter;

    @Autowired
    UserSummaryConverter userSummaryConverter;

    @Autowired
    LoanSummaryConverter loanSummaryConverter;

    public Reservation toEntity(CreateReservationDTO createReservationDTO, BookCopy bookCopy, User user, Loan loan) {
        Reservation reservation = new Reservation(bookCopy, loan, user);
        return reservation;
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation) {
        UUID id = reservation.getId();
        BookCopySummaryDTO bookCopy = bookCopySummaryConverter.toSummaryDTO(reservation.getBookCopy());
        UserSummaryDTO user = userSummaryConverter.toSummaryDTO(reservation.getUser());
        LocalDate reservationDate = reservation.getReservationDate();
        LocalDate expirationDate = reservation.getExpirationDate();
        ReservationStatus status = reservation.getStatus();
        LoanSummaryDTO loan = loanSummaryConverter.toSummaryDTO(reservation.getLoan());

        return new ReservationResponseDTO(id, bookCopy, user, reservationDate, expirationDate, status, loan);
    }

    public List<ReservationResponseDTO> toResponseListDTO(Collection<Reservation> reservations) {
        return reservations.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
