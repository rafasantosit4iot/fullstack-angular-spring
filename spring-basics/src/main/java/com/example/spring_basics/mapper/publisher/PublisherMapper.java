package com.example.spring_basics.mapper.publisher;

import java.util.UUID;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterSummaryDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;
import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;
import com.example.spring_basics.mapper.MappingCordinator;
import com.example.spring_basics.model.Publisher;

@Component
public class PublisherMapper {

    private final MappingCordinator mappingCordinator;

    public PublisherMapper(MappingCordinator mappingCordinator) {
        this.mappingCordinator = mappingCordinator;
    }

    public Publisher toEntity(CreatePublisherDTO createPublisherDTO) {
        Publisher publisher = new Publisher();

        publisher.setName(createPublisherDTO.name());
        publisher.setFounder(createPublisherDTO.founder());
        publisher.setFoundationYear(createPublisherDTO.foundationYear());

        return publisher;
    }

    // RESPONSE DTO
    // -------------------------------------------------------------------------------------------

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        UUID id = publisher.getId();
        String name = publisher.getName();
        String founder = publisher.getFounder();
        Integer foundationYear = publisher.getFoundationYear();
        List<HeadquarterSummaryDTO> headquarters = mappingCordinator
                .toHeadquarterSummaryList(publisher.getHeadquarters());

        PublisherResponseDTO publisherResponseDTO = new PublisherResponseDTO(id, name, founder, foundationYear,
                headquarters);
        return publisherResponseDTO;
    }

    public List<PublisherResponseDTO> toResponseListDTO(List<Publisher> publishers) {
        return publishers.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // SUMMARY DTO
    // -------------------------------------------------------------------------------------------

    public PublisherSummaryDTO toPublisherSummaryDTO(Publisher publisher) {
        UUID id = publisher.getId();
        String name = publisher.getName();
        String founder = publisher.getFounder();
        Integer foundationYear = publisher.getFoundationYear();

        PublisherSummaryDTO publisherSummaryDTO = new PublisherSummaryDTO(id, name, founder, foundationYear);
        return publisherSummaryDTO;
    }

    public List<PublisherSummaryDTO> toPublisherSummaryDTOList(Set<Publisher> publishers) {
        return publishers.stream()
                .map(this::toPublisherSummaryDTO)
                .collect(Collectors.toList());
    }
}
