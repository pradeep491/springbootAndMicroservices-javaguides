package com.test.springboot.mapper;

import com.test.springboot.dto.UserDTO;
import com.test.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDTO mapToUserDTO(User user);
    User mapToUser(UserDTO dto);
}
