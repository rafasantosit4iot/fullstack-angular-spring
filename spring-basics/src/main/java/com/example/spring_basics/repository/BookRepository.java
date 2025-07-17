package com.example.spring_basics.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
