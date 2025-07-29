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

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;
import com.example.spring_basics.service.headquarter.HeadquarterService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/headquarters")
public class HeadquarterController {
    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping
    public ResponseEntity<Page<HeadquarterResponseDTO>> getHeadquarters(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<HeadquarterResponseDTO> headquarters = headquarterService.getHeadquarters(pageNumber, pageSize);
        return ResponseEntity.ok(headquarters);
    }

    @PostMapping
    public ResponseEntity<HeadquarterResponseDTO> createHeadquarter(
            @Valid @RequestBody CreateHeadquarterDTO createHeadquarterDTO) {
        HeadquarterResponseDTO headquarterResponseDTO = headquarterService.createHeadquarter(createHeadquarterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(headquarterResponseDTO);
    }
}
