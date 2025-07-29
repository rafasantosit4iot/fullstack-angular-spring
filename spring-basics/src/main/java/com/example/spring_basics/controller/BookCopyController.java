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

import com.example.spring_basics.dto.request.book_copy.CreateBookCopyDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopyResponseDTO;
import com.example.spring_basics.service.book_copy.BookCopyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("book_copies")
public class BookCopyController {

    @Autowired
    BookCopyService bookCopyService;

    @GetMapping
    public ResponseEntity<Page<BookCopyResponseDTO>> getAllBookCopies(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<BookCopyResponseDTO> bookCopies = bookCopyService.getBookCopies(pageNumber, pageSize);
        return ResponseEntity.ok(bookCopies);
    }

    @PostMapping
    public ResponseEntity<BookCopyResponseDTO> createBookCopy(@Valid @RequestBody CreateBookCopyDTO createBookCopyDTO) {
        BookCopyResponseDTO bookCopyResponseDTO = bookCopyService.createBookCopy(createBookCopyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCopyResponseDTO);
    }
}
