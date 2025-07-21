package com.example.spring_basics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Headquarter;

public interface HeadquarterRepository extends JpaRepository<Headquarter, Integer>{
    
}
