package com.example.spring_basics.service.loan;

import java.util.List;

import com.example.spring_basics.dto.request.loan.CreateLoanDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;

public interface LoanService {
    public LoanResponseDTO createLoan(CreateLoanDTO createLoanDTO);

    public List<LoanResponseDTO> getAllLoans();
}
