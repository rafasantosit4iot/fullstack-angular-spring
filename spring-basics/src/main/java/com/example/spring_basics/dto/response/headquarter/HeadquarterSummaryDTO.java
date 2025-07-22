package com.example.spring_basics.dto.response.headquarter;

public record HeadquarterSummaryDTO(
        Integer id,
        String name,
        String street,
        String city,
        String countryName) {

}
