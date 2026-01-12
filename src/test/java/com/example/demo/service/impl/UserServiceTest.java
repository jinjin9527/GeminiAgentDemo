package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void should_calculate_offset_correctly_when_getting_all_users() {
        // Arrange
        int page = 2;
        int size = 5;
        int expectedOffset = 5; // (2 - 1) * 5
        User user = new User(1L, "test", "test@example.com", LocalDateTime.now());
        when(userMapper.findAll(expectedOffset, size)).thenReturn(List.of(user));

        // Act
        List<UserDTO> result = userService.getAllUsers(page, size);

        // Assert
        assertThat(result).hasSize(1);
        verify(userMapper).findAll(expectedOffset, size);
    }

    @Test
    void should_map_entity_to_dto_correctly() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "alice", "alice@example.com", now);
        when(userMapper.findById(1L)).thenReturn(user);

        // Act
        Optional<UserDTO> result = userService.getUserById(1L);

        // Assert
        assertThat(result).isPresent();
        UserDTO dto = result.get();
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.username()).isEqualTo("alice");
        assertThat(dto.email()).isEqualTo("alice@example.com");
        assertThat(dto.createdAt()).isEqualTo(now);
    }

    @Test
    void should_return_empty_list_when_mapper_returns_empty() {
        // Arrange
        when(userMapper.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        // Act
        List<UserDTO> result = userService.getAllUsers(1, 10);

        // Assert
        assertThat(result).isEmpty();
        assertThat(result).isNotNull();
    }

    @Test
    void should_return_empty_optional_when_user_not_found() {
        // Arrange
        when(userMapper.findById(99L)).thenReturn(null);

        // Act
        Optional<UserDTO> result = userService.getUserById(99L);

        // Assert
        assertThat(result).isEmpty();
    }
}
