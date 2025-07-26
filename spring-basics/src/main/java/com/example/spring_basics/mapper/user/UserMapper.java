package com.example.spring_basics.mapper.user;

import org.springframework.stereotype.Component;

import com.example.spring_basics.dto.request.user.CreateUserDTO;
import com.example.spring_basics.model.User;

@Component
public class UserMapper {
    public User toEntity(CreateUserDTO createUserDTO) {
        return new User();
    }
}
