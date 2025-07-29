package com.example.spring_basics.mapper.country;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;
import com.example.spring_basics.dto.response.country.CountryResponseDTO;
import com.example.spring_basics.model.Country;

@Component
public class CountryMapper {
    public Country toEntity(CreateCountryDTO createCountryDTO) {
        Country newCountry = new Country();

        newCountry.setName(createCountryDTO.name());
        newCountry.setIsoCode(createCountryDTO.isoCode());

        return newCountry;
    }

    public CountryResponseDTO toResponseDTO(Country country) {
        Integer id = country.getId();
        String name = country.getName();
        String isoCode = country.getIsoCode();

        // DTO
        CountryResponseDTO countryResponseDTO = new CountryResponseDTO(id, name, isoCode);
        return countryResponseDTO;
    }

    public Page<CountryResponseDTO> toResponseDTOList(Page<Country> countries) {
        return countries.map(this::toResponseDTO);
    }
}
