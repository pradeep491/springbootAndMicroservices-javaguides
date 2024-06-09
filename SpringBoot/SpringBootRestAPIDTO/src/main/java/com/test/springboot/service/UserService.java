package com.test.springboot.service;

import com.test.springboot.dto.UserDTO;
import com.test.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO user);
    void deleteUser(Long id);
}
