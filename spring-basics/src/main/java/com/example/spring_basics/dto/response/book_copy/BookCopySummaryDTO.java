package com.example.spring_basics.dto.response.book_copy;

import java.util.UUID;

import com.example.spring_basics.model.enums.CopyStatus;

public record BookCopySummaryDTO(
        UUID id,
        String classificationCode,
        String bookTitle,
        CopyStatus status
        ) {

}
