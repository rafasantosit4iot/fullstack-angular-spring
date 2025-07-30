package com.example.spring_basics.service.country;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.country.CreateCountryDTO;
import com.example.spring_basics.dto.response.country.CountryResponseDTO;
import com.example.spring_basics.mapper.country.CountryMapper;
import com.example.spring_basics.model.Country;
import com.example.spring_basics.repository.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public CountryResponseDTO createCountry(CreateCountryDTO createCountryDTO) {
        Country country = countryMapper.toEntity(createCountryDTO);
        country = countryRepository.save(country);
        return countryMapper.toResponseDTO(country);
    }

    @Override
    public Page<CountryResponseDTO> getCountries(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Country> countries = countryRepository.findAll(pageable);
        return countryMapper.toResponseDTOPage(countries);
    }

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        List<Country> allCountries = countryRepository.findAll(Sort.by("name"));
        return countryMapper.toResponseDTOList(allCountries);
    }
}
