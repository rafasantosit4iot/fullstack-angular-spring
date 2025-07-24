package com.example.spring_basics.service.book;

import java.util.List;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;

public interface BookService {
    BookResponseDTO createBook(CreateBookDTO createBookDTO);

    List<BookResponseDTO> getAllBooks();
}
