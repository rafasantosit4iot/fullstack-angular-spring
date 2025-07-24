package com.example.spring_basics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
    
}
