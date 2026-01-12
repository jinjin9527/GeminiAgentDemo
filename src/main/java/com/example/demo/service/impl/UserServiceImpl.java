package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User Service Implementation
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    // Constructor Injection
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers(int page, int size) {
        int offset = (page - 1) * size;
        List<User> users = userMapper.findAll(offset, size);
        return users.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        User user = userMapper.findById(id);
        return Optional.ofNullable(user)
                .map(UserDTO::fromEntity);
    }
}
