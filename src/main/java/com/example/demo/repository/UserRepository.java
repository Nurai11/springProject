package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users t WHERE t.login = :login")

    UserEntity getByLogin(@Param("login") String login);
}
