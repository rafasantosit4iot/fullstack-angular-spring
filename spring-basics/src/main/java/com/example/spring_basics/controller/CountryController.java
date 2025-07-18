package com.example.spring_basics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.response.country.CountryResponseDTO;
import com.example.spring_basics.service.CountryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryResponseDTO>> getAllCountries() {
        List<CountryResponseDTO> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<CountryResponseDTO> createCountry(@Valid @RequestBody CreateCountryDTO createCountryDTO) {
        CountryResponseDTO responseCountry = countryService.createCountry(createCountryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCountry);
    }
    
}
