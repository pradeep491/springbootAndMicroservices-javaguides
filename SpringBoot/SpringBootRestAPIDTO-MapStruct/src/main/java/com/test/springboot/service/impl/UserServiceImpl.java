package com.test.springboot.service.impl;

import com.test.springboot.dto.UserDTO;
import com.test.springboot.entity.User;
import com.test.springboot.mapper.AutoUserMapper;
import com.test.springboot.repository.UserRepository;
import com.test.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        //convert UserDTO into User JPA Entity
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = repository.save(user);
        //convert User JPA Entity to UserDTO
        UserDTO savedUserDto = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionaluser = repository.findById(id);
        User user = optionaluser.get();
        return AutoUserMapper.MAPPER.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDTO(user)).toList();

    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        //Approach-1
        User existingUser = repository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = repository.save(existingUser);
        return AutoUserMapper.MAPPER.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
