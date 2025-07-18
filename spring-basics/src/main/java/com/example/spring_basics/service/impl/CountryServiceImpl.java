package com.example.spring_basics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;
import com.example.spring_basics.dto.response.country.CountryResponseDTO;
import com.example.spring_basics.mapper.CountryMapper;
import com.example.spring_basics.model.Country;
import com.example.spring_basics.repository.CountryRepository;
import com.example.spring_basics.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryMapper countryMapper, CountryRepository countryRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryResponseDTO createCountry(CreateCountryDTO createCountryDTO) {
        Country country = countryMapper.toEntity(createCountryDTO);
        country = countryRepository.save(country);
        return countryMapper.toResponseDTO(country);
    }

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toResponseDTOList(countries);
    }
}
