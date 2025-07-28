package com.example.spring_basics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.response.book_copy.BookCopyResponseDTO;
import com.example.spring_basics.service.book_copy.BookCopyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("book_copies")
public class BookCopyController {
    
    @Autowired
    BookCopyService bookCopyService;

    @GetMapping
    public ResponseEntity<List<BookCopyResponseDTO>> getAllBookCopies() {
        List<BookCopyResponseDTO> bookCopies = bookCopyService.getAllBookCopies();
        return ResponseEntity.ok(bookCopies);
    }

}
