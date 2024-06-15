package com.springboot.service.impl;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.get();
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        //Approach-1
        User existingUser =  repository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return repository.save(existingUser);
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
