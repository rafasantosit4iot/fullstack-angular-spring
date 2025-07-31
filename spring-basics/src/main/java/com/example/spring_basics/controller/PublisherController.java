package com.example.spring_basics.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;
import com.example.spring_basics.service.publisher.PublisherService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Page<PublisherResponseDTO>> getPublishers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<PublisherResponseDTO> publishers = publisherService.getPublishers(pageNumber, pageSize);
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublisherResponseDTO>> getallAuthors() {
        List<PublisherResponseDTO> allPublishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(allPublishers);
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(
            @Valid @RequestBody CreatePublisherDTO createPublisherDTO) {
        PublisherResponseDTO responseDTO = publisherService.createPublisher(createPublisherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable UUID id) {
        this.publisherService.deletePublisher(id);
        return ResponseEntity.ok().build();
    }

}
