package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final UserRoleRepository userRoleRepository;
    final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity save(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(true);
        userRepository.save(user);

        if (user.getLogin().equalsIgnoreCase("admin")) {
            userRoleRepository.save(UserRoleEntity.builder()
                    .user(user)
                    .role(roleRepository.getById(2L))
                    .build());
        } else {
            userRoleRepository.save(UserRoleEntity.builder()
                    .user(user)
                    .role(roleRepository.getById(1L))
                    .build());
        }

        return user;
    }

    @Override
    public String getToken(UserDto userDto) {
        UserEntity user = userRepository.getByLogin(userDto.getLogin());
        if (user == null) {
            return null;
        }
        boolean isTrue = passwordEncoder.matches(userDto.getPassword(), user.getPassword());

        if (isTrue){
            return "Basic " + new String(Base64.getEncoder()
                    .encode((user.getLogin() + ":" + userDto.getPassword()).getBytes()));
        } else {
            return null;
        }
    }

    @Override
    public List<UserEntity> getAll () {
        return userRepository.findAll();
    }
}
