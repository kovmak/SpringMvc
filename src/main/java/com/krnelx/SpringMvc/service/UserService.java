package com.krnelx.SpringMvc.service;

import com.krnelx.SpringMvc.dto.UserDTO;
import com.krnelx.SpringMvc.model.User;
import com.krnelx.SpringMvc.mapper.UserMapper;
import com.krnelx.SpringMvc.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(userMapper::toDTO)
            .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? userMapper.toDTO(user) : null;
    }

    public UserDTO createOrUpdateUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}