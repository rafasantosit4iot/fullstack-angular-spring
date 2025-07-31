package com.example.spring_basics.mapper.reservation;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTOToUser;
import com.example.spring_basics.dto.response.reservation.ReservationResponseDTO;
import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.mapper.book_copy.BookCopySummaryConverter;
import com.example.spring_basics.mapper.loan.LoanSummaryConverter;
import com.example.spring_basics.mapper.user.UserSummaryConverter;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.ReservationStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final BookCopySummaryConverter bookCopySummaryConverter;
    private final UserSummaryConverter userSummaryConverter;
    private final LoanSummaryConverter loanSummaryConverter;

    public Reservation toEntity(ReservationStatus status, LocalDate reservationDate, LocalDate expirationDate, BookCopy bookCopy, User user) {
        Reservation reservation = new Reservation();

        reservation.setBookCopy(bookCopy);
        reservation.setUser(user);
        reservation.setReservationDate(reservationDate);
        reservation.setExpirationDate(expirationDate);
        reservation.setStatus(status);
        reservation.setLoan(null);

        return reservation;
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation) {
        UUID id = reservation.getId();
        BookCopySummaryDTO bookCopy = bookCopySummaryConverter.toSummaryDTO(reservation.getBookCopy());
        UserSummaryDTO user = userSummaryConverter.toSummaryDTO(reservation.getUser());
        LocalDate reservationDate = reservation.getReservationDate();
        LocalDate expirationDate = reservation.getExpirationDate();
        ReservationStatus status = reservation.getStatus();
        LoanSummaryDTOToUser loan = loanSummaryConverter.toSummaryDTOToUser(reservation.getLoan());

        return new ReservationResponseDTO(id, bookCopy, user, reservationDate, expirationDate, status, loan);
    }

    public Page<ReservationResponseDTO> toResponseListDTO(Page<Reservation> reservations) {
        return reservations.map(this::toResponseDTO);
    }
}
