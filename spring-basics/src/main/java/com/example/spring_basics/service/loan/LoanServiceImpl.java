package com.example.spring_basics.service.loan;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.loan.CreateLoanDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;
import com.example.spring_basics.mapper.loan.LoanMapper;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.Loan;
import com.example.spring_basics.model.User;
import com.example.spring_basics.model.enums.LoanStatus;
import com.example.spring_basics.repository.BookCopyRepository;
import com.example.spring_basics.repository.LoanRepository;
import com.example.spring_basics.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanMapper loanMapper;
    private final LoanCalculator loanCalculator;
    private final LoanRepository loanRepository;
    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;

    @Override
    public LoanResponseDTO createLoan(CreateLoanDTO createLoanDTO) {

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanCalculator.calculateDueDate(loanDate);
        LocalDate returnDate = null;
        double fineAmount = 0;
        LoanStatus status = LoanStatus.ACTIVE;

        BookCopy bookCopy = bookCopyRepository.findById(createLoanDTO.bookCopyId())
                .orElseThrow(() -> new EntityNotFoundException("Cópia não encontrada"));

        User user = userRepository.findById(createLoanDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Loan loan = loanMapper.toEntity(loanDate, dueDate, returnDate, fineAmount, status, bookCopy, user);
        loan = loanRepository.save(loan);
        return loanMapper.toResponseDTO(loan);
    }
    
    @Override
    public List<LoanResponseDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loanMapper.toResponseDTOList(loans);
    }
}
