package com.example.spring_basics.mapper.headquarter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.country.CountrySummaryDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterSummaryDTO;
import com.example.spring_basics.dto.response.publisher.PublisherSummaryDTO;
import com.example.spring_basics.mapper.MappingCordinator;
import com.example.spring_basics.model.Country;
import com.example.spring_basics.model.Headquarter;
import com.example.spring_basics.model.Publisher;

@Component
public class HeadquarterMapper {

    private final MappingCordinator mappingCordinator;

    public HeadquarterMapper(MappingCordinator mappingCordinator) {
        this.mappingCordinator = mappingCordinator;
    }

    public Headquarter toEntity(CreateHeadquarterDTO createHeadquarter, Publisher publisher, Country country) {
        Headquarter headquarter = new Headquarter();

        headquarter.setName(createHeadquarter.name());
        headquarter.setCity(createHeadquarter.city());
        headquarter.setState(createHeadquarter.state());
        headquarter.setStreet(createHeadquarter.street());
        headquarter.setNumber(createHeadquarter.number());
        headquarter.setZipCode(createHeadquarter.zipCode());

        headquarter.setPublisher(publisher);
        headquarter.setCountry(country);

        return headquarter;
    }

    // RESPONSE DTO
    // -------------------------------------------------------------------------------------------

    public HeadquarterResponseDTO toResponseDTO(Headquarter headquarter) {
        Integer id = headquarter.getId();
        String name = headquarter.getName();
        String street = headquarter.getStreet();
        String city = headquarter.getCity();

        CountrySummaryDTO countrySummaryDTO = mappingCordinator.toCountrySummary(headquarter.getCountry());
        PublisherSummaryDTO publisherSummaryDTO = mappingCordinator.toPublisherSummary(headquarter.getPublisher());

        HeadquarterResponseDTO headquarterResponseDTO = new HeadquarterResponseDTO(id, name, street, city,
                countrySummaryDTO, publisherSummaryDTO);

        return headquarterResponseDTO;
    }

    public List<HeadquarterResponseDTO> toResponseListDTO(List<Headquarter> headquarters) {
        return headquarters.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // SUMMARY DTO
    // -------------------------------------------------------------------------------------------

    public HeadquarterSummaryDTO toHeadquarterSummaryDTO(Headquarter headquarter) {
        Integer id = headquarter.getId();
        String name = headquarter.getName();
        String street = headquarter.getStreet();
        String city = headquarter.getCity();
        CountrySummaryDTO countrySummaryDTO = mappingCordinator.toCountrySummary(headquarter.getCountry());

        HeadquarterSummaryDTO headquarterSummaryDTO = new HeadquarterSummaryDTO(id, name, street, city,
                countrySummaryDTO);

        return headquarterSummaryDTO;
    }

    public List<HeadquarterSummaryDTO> toHeadquarterSummaryDTOList(Set<Headquarter> headquarters) {
        return headquarters.stream()
                .map(this::toHeadquarterSummaryDTO)
                .collect(Collectors.toList());
    }

}
