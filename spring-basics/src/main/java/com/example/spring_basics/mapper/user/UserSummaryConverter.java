package com.example.spring_basics.mapper.user;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.response.user.UserSummaryDTO;
import com.example.spring_basics.model.User;

@Component
public class UserSummaryConverter {
    public UserSummaryDTO toSummaryDTO(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLibraryUserCode());
    }
}
