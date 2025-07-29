package com.example.spring_basics.service.book_copy;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.book_copy.CreateBookCopyDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopyResponseDTO;

public interface BookCopyService {
    public BookCopyResponseDTO createBookCopy(CreateBookCopyDTO createBookCopyDTO);

    public Page<BookCopyResponseDTO> getBookCopies(int pageNumber, int pageSize);
}
