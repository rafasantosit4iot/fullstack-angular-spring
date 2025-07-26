package com.example.spring_basics.mapper.loan;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.loan.CreateLoanDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopySummaryDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;
import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.mapper.book_copy.BookCopySummaryConverter;
import com.example.spring_basics.mapper.user.UserSummaryConverter;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.LoanStatus;

@Component
public class LoanMapper {
    @Autowired
    UserSummaryConverter userSummaryConverter;

    @Autowired
    BookCopySummaryConverter bookCopySummaryConverter;

    public Loan toEntity(CreateLoanDTO createLoanDTO, BookCopy bookCopy, User user) {
        Loan loan = new Loan();
        loan.setBookCopy(bookCopy);
        loan.setUser(user);

        return loan;
    }

    public LoanResponseDTO toResponseDTO(Loan loan) {

        UUID id = loan.getId();
        LocalDate loanDate = loan.getLoanDate();
        LocalDate dueDate = loan.getDueDate();
        LocalDate returnDate = loan.getReturnDate();
        Integer fineAmount = loan.getFineAmount();
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
}
