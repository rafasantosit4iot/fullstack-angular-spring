package com.example.spring_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;
import com.example.spring_basics.service.book.BookService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public ResponseEntity<Page<BookResponseDTO>> getBooks(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<BookResponseDTO> books = bookService.getBooks(pageNumber, pageSize);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        BookResponseDTO bookResponseDTO = bookService.createBook(createBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }
}
