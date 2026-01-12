package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User Mapper Interface
 */
@Mapper
public interface UserMapper {

    /**
     * 分页查询所有用户
     * @param offset 偏移量
     * @param limit 每页数量
     * @return 用户列表
     */
    List<User> findAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象，若不存在则返回 null
     */
    User findById(@Param("id") Long id);
}
