package com.example.spring_basics.service.loan;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.loan.CreateLoanDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;
import com.example.spring_basics.mapper.loan.LoanMapper;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.CopyStatus;
import com.example.spring_basics.model.enums.LoanStatus;
import com.example.spring_basics.model.enums.ReservationStatus;
import com.example.spring_basics.repository.BookCopyRepository;
import com.example.spring_basics.repository.LoanRepository;
import com.example.spring_basics.repository.ReservationRepository;
import com.example.spring_basics.repository.UserRepository;
import com.example.spring_basics.service.book_copy.CopyValidator;
import com.sun.jdi.request.InvalidRequestStateException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanMapper loanMapper;
    private final LoanCalculator loanCalculator;
    private final LoanRepository loanRepository;
    private final BookCopyRepository bookCopyRepository;
    private final CopyValidator copyValidator;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public LoanResponseDTO createLoan(CreateLoanDTO createLoanDTO) {

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanCalculator.calculateDueDate(loanDate);
        LocalDate returnDate = null;
        double fineAmount = 0;
        LoanStatus status = LoanStatus.ACTIVE;

        BookCopy bookCopy = bookCopyRepository.findById(createLoanDTO.bookCopyId())
                .orElseThrow(() -> new EntityNotFoundException("Cópia não encontrada"));

        if (bookCopy.getStatus() != CopyStatus.AVAILABLE) {
            throw new InvalidRequestStateException("Livro não disponível para empréstimo");
        }

        User user = userRepository.findById(createLoanDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Reservation reservation = createLoanDTO.reservationId() != null
                ? reservationRepository.findById(createLoanDTO.reservationId())
                        .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"))
                : null;

        Loan loan = loanMapper.toEntity(loanDate, dueDate, returnDate, fineAmount, status, bookCopy, user, reservation);

        if (reservation != null) {
            if (!reservation.getUser().getId().equals(createLoanDTO.userId())) {
                throw new IllegalStateException("Reserva não pertence a este usuário");
            }

            if (reservation.getExpirationDate().isBefore(LocalDate.now())) {
                throw new IllegalStateException("Reserva expirada");
            }

            reservation.setLoan(loan);
            reservation.setStatus(ReservationStatus.COMPLETED);
            reservationRepository.save(reservation);
        }

        loan = loanRepository.save(loan);
        this.copyValidator.markBookCopyAsBorrowed(bookCopy);
        return loanMapper.toResponseDTO(loan);
    }

    @Override
    public Page<LoanResponseDTO> getLoans(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Loan> loans = loanRepository.findAll(pageable);
        return loanMapper.toResponseDTOList(loans);
    }
}
