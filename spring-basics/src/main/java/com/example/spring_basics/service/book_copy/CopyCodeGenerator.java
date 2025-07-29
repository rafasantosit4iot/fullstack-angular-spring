package com.example.spring_basics.service.book_copy;

import org.springframework.stereotype.Service;

import com.example.spring_basics.model.Book;

@Service
public class CopyCodeGenerator {
    public String generateBookCopyCode(Book book) {
        return "COD_1";
    }
}
