package com.example.spring_basics.service;

import java.util.List;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;

public interface PublisherService {
    List<PublisherResponseDTO> getAllPublishers();

    PublisherResponseDTO createPublisher(CreatePublisherDTO createPublisherDTO);
}
