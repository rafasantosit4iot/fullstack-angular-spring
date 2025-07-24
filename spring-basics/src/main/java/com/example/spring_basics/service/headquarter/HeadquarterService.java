package com.example.spring_basics.service.headquarter;

import java.util.List;

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;

public interface HeadquarterService {
    List<HeadquarterResponseDTO> getAllHeadquarters();

    HeadquarterResponseDTO createHeadquarter(CreateHeadquarterDTO createHeadquarterDTO);
}
