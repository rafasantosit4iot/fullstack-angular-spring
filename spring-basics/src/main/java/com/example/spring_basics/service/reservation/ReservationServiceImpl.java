package com.example.spring_basics.service.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.reservation.CreateReservationDTO;
import com.example.spring_basics.dto.response.reservation.ReservationResponseDTO;
import com.example.spring_basics.mapper.book_copy.BookCopyMapper;
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

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationMapper reservationMapper;
    ReservationRepository reservationRepository;
    BookCopyRepository bookCopyRepository;
    UserRepository userRepository;
    LoanRepository loanRepository;

    @Override
    public ReservationResponseDTO createReservation(CreateReservationDTO createReservationDTO) {
        BookCopy bookCopy = bookCopyRepository.findById(createReservationDTO.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Cópia de livro nçao encontrada"));
        User user = userRepository.findById(createReservationDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        Loan loan = loanRepository.findById(createReservationDTO.loanId())
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));

        Reservation reservation = reservationMapper.toEntity(createReservationDTO, bookCopy, user, loan);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.toResponseDTO(reservation);
    }
    
    @Override
    public List<ReservationResponseDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toResponseListDTO(reservations);
    }
}
