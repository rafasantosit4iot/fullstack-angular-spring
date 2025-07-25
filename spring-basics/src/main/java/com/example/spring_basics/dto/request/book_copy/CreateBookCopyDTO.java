package com.example.spring_basics.dto.request.book_copy;

import org.hibernate.validator.constraints.UUID;

import com.example.spring_basics.model.enums.CopyStatus;

public record CreateBookCopyDTO(
        CopyStatus status,
        UUID bookId
        ) {

}
