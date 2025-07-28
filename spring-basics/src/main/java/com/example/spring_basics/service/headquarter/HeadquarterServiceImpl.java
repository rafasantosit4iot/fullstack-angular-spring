package com.example.spring_basics.service.headquarter;

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

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeadquarterServiceImpl implements HeadquarterService {
    private final HeadquarterRepository headquarterRepository;
    private final HeadquarterMapper headquarterMapper;
    private final PublisherRepository publisherRepository;
    private final CountryRepository countryRepository;

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
