package com.example.spring_basics.dto.response.user;

import java.util.UUID;

public record UserSummaryDTO(
        UUID id,
        String name,
        String email,
        String libraryUserCode) {

}
