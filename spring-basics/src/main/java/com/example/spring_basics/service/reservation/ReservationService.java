package com.example.spring_basics.service.reservation;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.reservation.CreateReservationDTO;
import com.example.spring_basics.dto.response.reservation.ReservationResponseDTO;

public interface ReservationService {
    ReservationResponseDTO createReservation(CreateReservationDTO createReservationDTO);

    Page<ReservationResponseDTO> getReservations(int pageNumber, int pageSize);
}
