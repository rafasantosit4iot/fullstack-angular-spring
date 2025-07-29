package com.example.spring_basics.service.book;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;

public interface BookService {
    BookResponseDTO createBook(CreateBookDTO createBookDTO);

    Page<BookResponseDTO> getBooks(int pageNumber, int pageSize);
}
