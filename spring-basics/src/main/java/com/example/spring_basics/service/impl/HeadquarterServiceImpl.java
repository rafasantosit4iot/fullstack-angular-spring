package com.example.spring_basics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;
import com.example.spring_basics.mapper.headquarter.HeadquarterMapper;
import com.example.spring_basics.model.Country;
import com.example.spring_basics.model.Headquarter;
import com.example.spring_basics.model.Publisher;
import com.example.spring_basics.repository.CountryRepository;
import com.example.spring_basics.repository.HeadquarterRepository;
import com.example.spring_basics.repository.PublisherRepository;
import com.example.spring_basics.service.HeadquarterService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HeadquarterServiceImpl implements HeadquarterService {
    private final HeadquarterRepository headquarterRepository;
    private final HeadquarterMapper headquarterMapper;
    private final PublisherRepository publisherRepository;
    private final CountryRepository countryRepository;

    public HeadquarterServiceImpl(CountryRepository countryRepository, HeadquarterMapper headquarterMapper, HeadquarterRepository headquarterRepository, PublisherRepository publisherRepository) {
        this.countryRepository = countryRepository;
        this.headquarterMapper = headquarterMapper;
        this.headquarterRepository = headquarterRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<HeadquarterResponseDTO> getAllHeadquarters() {
        List<Headquarter> headquarters = headquarterRepository.findAll();
        return headquarterMapper.toResponseListDTO(headquarters);
    }

    @Override
    public HeadquarterResponseDTO createHeadquarter(CreateHeadquarterDTO createHeadquarterDTO) {
        Publisher publisher = publisherRepository.findById(createHeadquarterDTO.publisherId())
            .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada"));

        Country country = countryRepository.findById(createHeadquarterDTO.countryId())
            .orElseThrow(() -> new EntityNotFoundException("País não encontrado"));

        Headquarter headquarter = headquarterMapper.toEntity(createHeadquarterDTO, publisher, country);
        headquarter = headquarterRepository.save(headquarter);
        return headquarterMapper.toResponseDTO(headquarter);
    }
}
