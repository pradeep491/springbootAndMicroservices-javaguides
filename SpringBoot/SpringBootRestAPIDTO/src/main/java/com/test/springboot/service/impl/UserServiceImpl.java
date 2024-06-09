package com.test.springboot.service.impl;

import com.test.springboot.dto.UserDTO;
import com.test.springboot.entity.User;
import com.test.springboot.mapper.UserMapper;
import com.test.springboot.repository.UserRepository;
import com.test.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = UserMapper.mapToUser(userDto);
        //convert UserDTO into User JPA Entity
        User savedUser = repository.save(user);
        //convert User JPA Entity to UserDTO
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionaluser = repository.findById(id);
        User user = optionaluser.get();
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        return users.stream().map(UserMapper::mapToUserDTO).toList();
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        //Approach-1
        User existingUser = repository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser =  repository.save(existingUser);
        return UserMapper.mapToUserDTO(updatedUser);
        //Approach-2
        /*Optional<User> existingUser = repository.findById(user.getId());
        if (existingUser.isPresent()) {
            User user1 = existingUser.get();
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setEmail(user.getEmail());
            return repository.save(user1);
        } else {
            throw new RuntimeException("User Not Found for the id-" + user.getId());
        }*/
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
