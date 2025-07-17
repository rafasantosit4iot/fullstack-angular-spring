package com.example.spring_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.repository.BookRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4201")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
}
