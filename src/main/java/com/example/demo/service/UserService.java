package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserEntity save(UserEntity user);

    String getToken(UserDto userDto);

    List<UserEntity> getAll();
}
