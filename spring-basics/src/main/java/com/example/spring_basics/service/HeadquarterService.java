package com.example.spring_basics.service;

import java.util.List;

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;

public interface HeadquarterService {
    List<HeadquarterResponseDTO> getAllHeadquarters();

    HeadquarterResponseDTO createHeadquarter(CreateHeadquarterDTO createHeadquarterDTO);
}
