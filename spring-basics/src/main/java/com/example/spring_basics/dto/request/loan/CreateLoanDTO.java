package com.example.spring_basics.dto.request.loan;

import java.util.UUID;

public record CreateLoanDTO(
    UUID bookCopyId,
    UUID userId
) {
    
}
