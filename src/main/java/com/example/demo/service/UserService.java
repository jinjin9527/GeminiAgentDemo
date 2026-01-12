package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import java.util.List;
import java.util.Optional;

/**
 * User Service Interface
 */
public interface UserService {

    /**
     * 分页获取所有用户
     * @param page 页码 (从1开始)
     * @param size 每页大小
     * @return 用户DTO列表
     */
    List<UserDTO> getAllUsers(int page, int size);

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 包含用户DTO的 Optional，若不存在则为空
     */
    Optional<UserDTO> getUserById(Long id);
}
