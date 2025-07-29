package com.example.spring_basics.service.country;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;
import com.example.spring_basics.dto.response.country.CountryResponseDTO;

public interface CountryService {
    CountryResponseDTO createCountry(CreateCountryDTO createCountryDTO);

    Page<CountryResponseDTO> getCountries(int pageNumber, int pageSize);
}
