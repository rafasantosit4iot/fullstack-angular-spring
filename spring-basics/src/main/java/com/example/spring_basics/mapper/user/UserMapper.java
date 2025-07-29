package com.example.spring_basics.mapper.user;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.user.CreateUserDTO;
import com.example.spring_basics.dto.response.loan.LoanSummaryDTOToUser;
import com.example.spring_basics.dto.response.reservation.ReservationSummaryDTO;
import com.example.spring_basics.dto.response.user.UserResponseDTO;
import com.example.spring_basics.mapper.loan.LoanSummaryConverter;
import com.example.spring_basics.mapper.reservation.ReservationSummaryConverter;
import com.example.spring_basics.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final LoanSummaryConverter loanSummaryConverter;
    private final ReservationSummaryConverter reservationSummaryConverter;

    public User toEntity(CreateUserDTO createUserDTO, String libraryUserCode) {
        User user = new User();

        user.setName(createUserDTO.name());
        user.setEmail(createUserDTO.email());
        user.setLibraryUserCode(libraryUserCode);
        user.setMaxActiveLoans(createUserDTO.maxActiveLoans());
        user.setActive(createUserDTO.active());

        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UUID id = user.getId();
        String name = user.getName();
        String email = user.getEmail();
        String libraryUserCode = user.getLibraryUserCode();
        Boolean active = user.getActive();
        List<LoanSummaryDTOToUser> loans = loanSummaryConverter.toUserSummaryList(user.getLoans());
        List<ReservationSummaryDTO> reservations = reservationSummaryConverter.toSummaryDTOList(user.getReservations());

        UserResponseDTO userResponseDTO = new UserResponseDTO(id, name, email, libraryUserCode, active, loans,
                reservations);
        return userResponseDTO;
    }

    public Page<UserResponseDTO> toResponseDTOList(Page<User> users) {
        return users.map(this::toResponseDTO);
    }
}
