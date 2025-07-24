package com.example.spring_basics.dto.response.headquarter;

import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;

public record HeadquarterResponseDTO(
        Integer id,
        String street,
        String city,
        CountrySummaryDTO country,
        PublisherSummaryDTO publisher) {

}
