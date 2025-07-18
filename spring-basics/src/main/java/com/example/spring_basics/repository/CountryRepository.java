package com.example.spring_basics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_basics.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{
    
}
