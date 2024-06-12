package com.test.springboot.mapper;

import com.test.springboot.dto.UserDTO;
import com.test.springboot.entity.User;

public class UserMapper {
    //Convert JPA entity into User DTO
    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId()
                , user.getFirstName()
                , user.getLastName()
                , user.getEmail());
    }

    //Convert UserDTO into User JPA Entity
    public static User mapToUser(UserDTO dto) {
        return new User(dto.getId(),
                             dto.getFirstName(),
                             dto.getLastName(),
                             dto.getEmail());
    }
}
