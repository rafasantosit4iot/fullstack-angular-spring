package com.example.spring_basics.mapper.reservation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.reservation.ReservationSummaryDTO;
import com.example.spring_basics.model.Reservation;

@Component
public class ReservationSummaryConverter {

    public ReservationSummaryDTO toSummaryDTO(Reservation reservation) {
        return new ReservationSummaryDTO(
                reservation.getId(),
                reservation.getBookCopy().getBook().getTitle(),
                reservation.getUser().getName(),
                reservation.getReservationDate(),
                reservation.getExpirationDate(),
                reservation.getStatus(),
                reservation.getLoan().getId());
    }

    public List<ReservationSummaryDTO> toSummaryDTOList(Collection<Reservation> reservations) {
        return reservations.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
