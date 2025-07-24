package com.example.spring_basics.service.country;

import java.util.List;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;
import com.example.spring_basics.dto.response.country.CountryResponseDTO;

public interface CountryService {
    CountryResponseDTO createCountry(CreateCountryDTO createCountryDTO);

    List<CountryResponseDTO> getAllCountries();
}
