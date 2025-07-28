package com.example.spring_basics.service.reservation;

import java.util.List;

import com.example.spring_basics.dto.request.reservation.CreateReservationDTO;
import com.example.spring_basics.dto.response.reservation.ReservationResponseDTO;

public interface ReservationService {
    ReservationResponseDTO createReservation(CreateReservationDTO createReservationDTO);

    List<ReservationResponseDTO> getAllReservations();
}
