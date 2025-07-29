package com.example.spring_basics.service.reservation;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.reservation.CreateReservationDTO;
import com.example.spring_basics.dto.response.reservation.ReservationResponseDTO;
import com.example.spring_basics.mapper.reservation.ReservationMapper;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.User;
import com.example.spring_basics.repository.BookCopyRepository;
import com.example.spring_basics.repository.LoanRepository;
import com.example.spring_basics.repository.ReservationRepository;
import com.example.spring_basics.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

        private final ReservationMapper reservationMapper;
        private final ReservationRepository reservationRepository;
        private final ReservationCalculator reservationCalculator;
        private final BookCopyRepository bookCopyRepository;
        private final UserRepository userRepository;
        private final LoanRepository loanRepository;

        @Override
        public ReservationResponseDTO createReservation(CreateReservationDTO createReservationDTO) {
                LocalDate reservationDate = LocalDate.now();
                LocalDate expirationDate = reservationCalculator.calculateExpirationDate(reservationDate);

                BookCopy bookCopy = bookCopyRepository.findById(createReservationDTO.bookCopyId())
                                .orElseThrow(() -> new EntityNotFoundException("Cópia de livro nçao encontrada"));
                User user = userRepository.findById(createReservationDTO.userId())
                                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
                Loan loan = loanRepository.findById(createReservationDTO.loanId())
                                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));

                Reservation reservation = reservationMapper.toEntity(createReservationDTO, reservationDate,
                                expirationDate,
                                bookCopy, user, loan);
                reservation = reservationRepository.save(reservation);
                return reservationMapper.toResponseDTO(reservation);
        }

        @Override
        public Page<ReservationResponseDTO> getReservations(int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Reservation> reservations = reservationRepository.findAll(pageable);
                return reservationMapper.toResponseListDTO(reservations);
        }
}
