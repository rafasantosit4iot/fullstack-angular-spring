package com.example.spring_basics.service.loan;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.loan.CreateLoanDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;

public interface LoanService {
    public LoanResponseDTO createLoan(CreateLoanDTO createLoanDTO);

    public Page<LoanResponseDTO> getLoans(int pageNumber,int pageSize);
}
