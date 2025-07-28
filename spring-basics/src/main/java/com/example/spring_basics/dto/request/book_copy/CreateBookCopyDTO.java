package com.example.spring_basics.dto.request.book_copy;

import java.util.UUID;


public record CreateBookCopyDTO(
        UUID bookId
        ) {

}
