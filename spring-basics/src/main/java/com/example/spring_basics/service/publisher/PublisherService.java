package com.example.spring_basics.service.publisher;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;

public interface PublisherService {
    Page<PublisherResponseDTO> getPublishers(int pageNumber, int pageSize);

    PublisherResponseDTO createPublisher(CreatePublisherDTO createPublisherDTO);

    void deletePublisher(UUID publisherId);
}
