package com.example.spring_basics.mapper.headquarter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.headquarter.HeadquarterSummaryDTO;
import com.example.spring_basics.model.Headquarter;

@Component
public class HeadquarterSummaryConverter {
    public HeadquarterSummaryDTO toSummaryDTO(Headquarter headquarter) {
        return new HeadquarterSummaryDTO(
                headquarter.getId(),
                headquarter.getStreet(),
                headquarter.getCity(),
                headquarter.getCountry().getName());
    }

    public List<HeadquarterSummaryDTO> toSummaryList(Collection<Headquarter> headquarters) {
        return headquarters.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}
