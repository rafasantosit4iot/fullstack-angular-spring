package com.example.spring_basics.service.user;

import org.springframework.data.domain.Page;

import com.example.spring_basics.dto.request.user.CreateUserDTO;
import com.example.spring_basics.dto.response.user.UserResponseDTO;

public interface UserService {
    public UserResponseDTO createUser(CreateUserDTO createUserDTO);

    public Page<UserResponseDTO> getUsers(int pageNumber, int pageSize);
}
