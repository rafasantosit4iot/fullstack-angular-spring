package com.example.spring_basics.dto.response.headquarter;

import com.example.spring_basics.dto.response.country.CountrySummaryDTO;

public record HeadquarterSummaryDTO(
        Integer id,
        String name,
        String street,
        String city,
        CountrySummaryDTO countrySummaryDTO) {

}
