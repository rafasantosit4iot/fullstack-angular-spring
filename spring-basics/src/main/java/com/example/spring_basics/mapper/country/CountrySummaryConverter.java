package com.example.spring_basics.mapper.country;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.model.Country;

@Component
public class CountrySummaryConverter {
    public CountrySummaryDTO toSummaryDTO(Country country) {
        return new CountrySummaryDTO(
                country.getId(),
                country.getName());
    }
}
