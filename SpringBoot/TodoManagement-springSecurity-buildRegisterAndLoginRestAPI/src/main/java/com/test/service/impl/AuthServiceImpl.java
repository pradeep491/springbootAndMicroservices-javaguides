package com.test.service.impl;

import com.test.dto.RegisterDTO;
import com.test.entity.Role;
import com.test.entity.User;
import com.test.exception.TodoAPIException;
import com.test.repos.RoleRepository;
import com.test.repos.UserRepository;
import com.test.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String register(RegisterDTO registerDTO) {
        //if the username is already exists in db or not
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists in the system");
        }
        //check whether the eamil is already exists in DB
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "User Email already exists in the system");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());

        //Need to get role object and assign
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);
        return "User Registered successfully...!";
    }
}
