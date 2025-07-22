package com.example.spring_basics.mapper.publisher;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;
import com.example.spring_basics.model.Publisher;

@Component
public class PublisherSummaryConverter {
    public PublisherSummaryDTO toSummaryDTO(Publisher publisher) {
        return new PublisherSummaryDTO(
                publisher.getId(),
                publisher.getName(),
                publisher.getFounder(),
                publisher.getFoundationYear());
    }
}
