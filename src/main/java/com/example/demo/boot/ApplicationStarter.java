package com.example.demo.boot;

import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class ApplicationStarter implements CommandLineRunner {
    final RoleRepository roleRepository;

    @Autowired
    public ApplicationStarter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(RoleEntity.builder().name("ROLE_USER").build());
        roleRepository.save(RoleEntity.builder().name("ROLE_ADMIN").build());
    }
}
