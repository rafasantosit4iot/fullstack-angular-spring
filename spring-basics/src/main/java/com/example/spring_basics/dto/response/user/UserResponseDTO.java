package com.example.spring_basics.dto.response.user;

import java.util.List;
import java.util.UUID;

import com.example.spring_basics.dto.response.loan.LoanSummaryDTOToUser;
import com.example.spring_basics.dto.response.reservation.ReservationSummaryDTO;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String libraryUserCode,
        Boolean active,
        List<LoanSummaryDTOToUser> loans,
        List<ReservationSummaryDTO> reservations

) {

}
