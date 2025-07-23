package com.example.spring_basics.mapper.headquarter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;
import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;
import com.example.spring_basics.mapper.country.CountrySummaryConverter;
import com.example.spring_basics.mapper.publisher.PublisherSummaryConverter;
import com.example.spring_basics.model.Country;
import com.example.spring_basics.model.Headquarter;
import com.example.spring_basics.model.Publisher;

@Component
public class HeadquarterMapper {

    @Autowired
    CountrySummaryConverter countrySummaryConverter;

    @Autowired
    PublisherSummaryConverter publisherSummaryConverter;

    public Headquarter toEntity(CreateHeadquarterDTO createHeadquarter, Publisher publisher, Country country) {
        Headquarter headquarter = new Headquarter();

        headquarter.setCity(createHeadquarter.city());
        headquarter.setState(createHeadquarter.state());
        headquarter.setStreet(createHeadquarter.street());
        headquarter.setNumber(createHeadquarter.number());
        headquarter.setZipCode(createHeadquarter.zipCode());

        headquarter.setPublisher(publisher);
        headquarter.setCountry(country);

        return headquarter;
    }

    public HeadquarterResponseDTO toResponseDTO(Headquarter headquarter) {
        Integer id = headquarter.getId();
        String street = headquarter.getStreet();
        String city = headquarter.getCity();

        CountrySummaryDTO countrySummaryDTO = countrySummaryConverter.toSummaryDTO(headquarter.getCountry());
        PublisherSummaryDTO publisherSummaryDTO = publisherSummaryConverter.toSummaryDTO(headquarter.getPublisher());

        HeadquarterResponseDTO headquarterResponseDTO = new HeadquarterResponseDTO(id, street, city,
                countrySummaryDTO, publisherSummaryDTO);

        return headquarterResponseDTO;
    }

    public List<HeadquarterResponseDTO> toResponseListDTO(List<Headquarter> headquarters) {
        return headquarters.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
