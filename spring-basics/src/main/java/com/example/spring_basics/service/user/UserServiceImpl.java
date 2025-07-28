package com.example.spring_basics.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.user.CreateUserDTO;
import com.example.spring_basics.dto.response.user.UserResponseDTO;
import com.example.spring_basics.mapper.user.UserMapper;
import com.example.spring_basics.model.User;
import com.example.spring_basics.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserLibraryCode userLibraryCode;

    @Override
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        String libraryCode = userLibraryCode.generateLibraryUserCode(createUserDTO.name());

        User user = userMapper.toEntity(createUserDTO, libraryCode);
        user = userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }
    
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseDTOList(users);
    }
}
