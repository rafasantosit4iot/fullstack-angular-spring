package com.example.spring_basics.mapper.loan;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;
import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.mapper.book_copy.BookCopySummaryConverter;
import com.example.spring_basics.mapper.user.UserSummaryConverter;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.Reservation;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.LoanStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoanMapper {
    private final UserSummaryConverter userSummaryConverter;
    private final BookCopySummaryConverter bookCopySummaryConverter;

    public Loan toEntity(LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, double fineAmount,
            LoanStatus status, BookCopy bookCopy, User user, Reservation reservation) {
        Loan loan = new Loan();

        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);
        loan.setReturnDate(returnDate);
        loan.setFineAmount(fineAmount);
        loan.setStatus(status);
        loan.setBookCopy(bookCopy);
        loan.setUser(user);

        if (reservation != null) {
            loan.setReservation(reservation);
        }

        return loan;
    }

    public LoanResponseDTO toResponseDTO(Loan loan) {
        UUID id = loan.getId();
        LocalDate loanDate = loan.getLoanDate();
        LocalDate dueDate = loan.getDueDate();
        LocalDate returnDate = loan.getReturnDate();
        double fineAmount = loan.getFineAmount();
        LoanStatus status = loan.getStatus();
        BookCopySummaryDTO bookCopy = bookCopySummaryConverter.toSummaryDTO(loan.getBookCopy());
        UserSummaryDTO user = userSummaryConverter.toSummaryDTO(loan.getUser());

        return new LoanResponseDTO(
                id,
                loanDate,
                dueDate,
                returnDate,
                fineAmount,
                status,
                bookCopy,
                user);
    }

    public Page<LoanResponseDTO> toResponseDTOList(Page<Loan> loans) {
        return loans.map(this::toResponseDTO);
    }
}
