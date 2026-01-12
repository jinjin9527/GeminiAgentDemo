package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void should_return_ok_and_list_when_getting_all_users() {
        // Arrange
        UserDTO dto = new UserDTO(1L, "bob", "bob@example.com", LocalDateTime.now());
        when(userService.getAllUsers(1, 10)).thenReturn(List.of(dto));

        // Act
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers(1, 10);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).username()).isEqualTo("bob");
    }

    @Test
    void should_return_ok_when_user_found() {
        // Arrange
        UserDTO dto = new UserDTO(1L, "charlie", "charlie@example.com", LocalDateTime.now());
        when(userService.getUserById(1L)).thenReturn(Optional.of(dto));

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().username()).isEqualTo("charlie");
    }

    @Test
    void should_return_not_found_when_user_does_not_exist() {
        // Arrange
        when(userService.getUserById(99L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(99L);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }
}
