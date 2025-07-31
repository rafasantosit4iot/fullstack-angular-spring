package com.example.spring_basics.mapper.publisher;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.publisher.CreatePublisherDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterSummaryDTO;
import com.example.spring_basics.dto.response.publisher.PublisherResponseDTO;
import com.example.spring_basics.mapper.headquarter.HeadquarterSummaryConverter;
import com.example.spring_basics.model.Publisher;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PublisherMapper {
    private final HeadquarterSummaryConverter headquarterSummaryConverter;

    public Publisher toEntity(CreatePublisherDTO createPublisherDTO) {
        Publisher publisher = new Publisher();

        publisher.setName(createPublisherDTO.name());
        publisher.setFounder(createPublisherDTO.founder());
        publisher.setFoundationYear(createPublisherDTO.foundationYear());

        return publisher;
    }

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        UUID id = publisher.getId();
        String name = publisher.getName();
        String founder = publisher.getFounder();
        Integer foundationYear = publisher.getFoundationYear();
        List<HeadquarterSummaryDTO> headquarters = headquarterSummaryConverter
                .toSummaryList(publisher.getHeadquarters());

        PublisherResponseDTO publisherResponseDTO = new PublisherResponseDTO(id, name, founder, foundationYear,
                headquarters);
        return publisherResponseDTO;
    }

    public Page<PublisherResponseDTO> toResponseDTOPage(Page<Publisher> publishers) {
        return publishers.map(this::toResponseDTO);
    }

    public List<PublisherResponseDTO> toResponseDTOList(List<Publisher> publishers) {
        return publishers.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
