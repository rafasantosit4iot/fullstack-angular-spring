package com.example.spring_basics.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
