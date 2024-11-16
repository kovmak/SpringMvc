package com.krnelx.SpringMvc.mapper;

import com.krnelx.SpringMvc.dto.UserDTO;
import com.krnelx.SpringMvc.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}