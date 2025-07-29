package com.example.spring_basics.mapper.headquarter;

import org.springframework.data.domain.Page;
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

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HeadquarterMapper {

    private final CountrySummaryConverter countrySummaryConverter;
    private final PublisherSummaryConverter publisherSummaryConverter;

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
        String state = headquarter.getState();
        Integer number = headquarter.getNumber();
        CountrySummaryDTO countrySummaryDTO = countrySummaryConverter.toSummaryDTO(headquarter.getCountry());
        PublisherSummaryDTO publisherSummaryDTO = publisherSummaryConverter.toSummaryDTO(headquarter.getPublisher());

        HeadquarterResponseDTO headquarterResponseDTO = new HeadquarterResponseDTO(
                id,
                street,
                city,
                state,
                number,
                countrySummaryDTO,
                publisherSummaryDTO);
        return headquarterResponseDTO;
    }

    public Page<HeadquarterResponseDTO> toResponseListDTO(Page<Headquarter> headquarters) {
        return headquarters.map(this::toResponseDTO);
    }
}
