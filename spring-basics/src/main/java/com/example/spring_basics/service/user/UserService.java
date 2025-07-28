package com.example.spring_basics.service.user;

import java.util.List;

import com.example.spring_basics.dto.request.user.CreateUserDTO;
import com.example.spring_basics.dto.response.user.UserResponseDTO;

public interface UserService {
    public UserResponseDTO createUser(CreateUserDTO createUserDTO);

    public List<UserResponseDTO> getAllUsers();
}
