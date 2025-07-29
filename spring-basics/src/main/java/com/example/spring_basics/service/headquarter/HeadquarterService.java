package com.example.spring_basics.service.headquarter;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.headquarter.CreateHeadquarterDTO;
import com.example.spring_basics.dto.response.headquarter.HeadquarterResponseDTO;

public interface HeadquarterService {
    Page<HeadquarterResponseDTO> getHeadquarters(int pageNumber, int pageSize);

    HeadquarterResponseDTO createHeadquarter(CreateHeadquarterDTO createHeadquarterDTO);
}
