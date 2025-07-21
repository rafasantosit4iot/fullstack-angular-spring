package com.example.spring_basics.mapper.country;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;
import com.example.spring_basics.dto.response.country.CountryResponseDTO;
import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.model.Country;

@Component
public class CountryMapper {
    public Country toEntity(CreateCountryDTO createCountryDTO) {
        Country newCountry = new Country();

        newCountry.setName(createCountryDTO.name());
        newCountry.setIsoCode(createCountryDTO.isoCode());

        return newCountry;
    }

    // RESPONSE DTO
    // -------------------------------------------------------------------------------------------

    public CountryResponseDTO toResponseDTO(Country country) {
        Integer id = country.getId();
        String name = country.getName();
        String isoCode = country.getIsoCode();

        // DTO
        CountryResponseDTO countryResponseDTO = new CountryResponseDTO(id, name, isoCode);
        return countryResponseDTO;
    }

    public List<CountryResponseDTO> toResponseDTOList(List<Country> countries) {
        return countries.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    // SUMMARY DTO
    // -------------------------------------------------------------------------------------------

    public CountrySummaryDTO toCountrySummaryDTO(Country country) {
        Integer id = country.getId();
        String name = country.getName();

        CountrySummaryDTO countrySummaryDTO = new CountrySummaryDTO(id, name);
        return countrySummaryDTO;
    }
}
