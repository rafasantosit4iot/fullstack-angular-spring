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
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.CopyStatus;
import com.example.spring_basics.model.enums.ReservationStatus;
import com.example.spring_basics.repository.BookCopyRepository;
import com.example.spring_basics.repository.ReservationRepository;
import com.example.spring_basics.repository.UserRepository;
import com.example.spring_basics.service.book_copy.CopyValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

        private final ReservationMapper reservationMapper;
        private final ReservationRepository reservationRepository;
        private final ReservationCalculator reservationCalculator;
        private final BookCopyRepository bookCopyRepository;
        private final CopyValidator copyValidator;
        private final UserRepository userRepository;

        @Override
        public ReservationResponseDTO createReservation(CreateReservationDTO createReservationDTO) {
                LocalDate reservationDate = LocalDate.now();
                LocalDate expirationDate = reservationCalculator.calculateExpirationDate(reservationDate);
                ReservationStatus status = ReservationStatus.ACTIVE;

                BookCopy bookCopy = bookCopyRepository.findById(createReservationDTO.bookCopyId())
                                .orElseThrow(() -> new EntityNotFoundException("Cópia de livro nçao encontrada"));
                User user = userRepository.findById(createReservationDTO.userId())
                                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

                // Verificar se cópia já está reservada/emprestada
                if (bookCopy.getStatus() != CopyStatus.AVAILABLE) {
                        throw new IllegalStateException("Cópia não está disponível para reserva");
                }

                // Verificar limite de reservas do usuário
                long activeReservations = reservationRepository.countByUserIdAndStatus(createReservationDTO.userId(),
                                ReservationStatus.ACTIVE);

                if (activeReservations >= user.getMaxActiveLoans()) {
                        throw new IllegalStateException("Limite de reservas atingido");
                }

                Reservation reservation = reservationMapper.toEntity(status, reservationDate,
                                expirationDate,
                                bookCopy, user);
                reservation = reservationRepository.save(reservation);
                copyValidator.markBookCopyAsReserved(bookCopy);
                return reservationMapper.toResponseDTO(reservation);
        }

        @Override
        public Page<ReservationResponseDTO> getReservations(int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Reservation> reservations = reservationRepository.findAll(pageable);
                return reservationMapper.toResponseListDTO(reservations);
        }
}
