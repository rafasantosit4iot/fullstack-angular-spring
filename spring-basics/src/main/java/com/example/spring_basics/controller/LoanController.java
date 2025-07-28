package com.example.spring_basics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.request.loan.CreateLoanDTO;
import com.example.spring_basics.dto.response.loan.LoanResponseDTO;
import com.example.spring_basics.service.loan.LoanService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody CreateLoanDTO createLoanDTO) {
        LoanResponseDTO loanResponseDTO = loanService.createLoan(createLoanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponseDTO);
    }
}
