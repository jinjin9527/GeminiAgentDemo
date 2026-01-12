package com.example.demo.dto;

import com.example.demo.entity.User;
import java.time.LocalDateTime;

/**
 * User Data Transfer Object
 * Using Java Record for immutability and concise syntax (Java 25 style)
 */
public record UserDTO(Long id, String username, String email, LocalDateTime createdAt) {

    /**
     * Static factory method to convert Entity to DTO
     * @param user User Entity
     * @return UserDTO
     */
    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }
}
