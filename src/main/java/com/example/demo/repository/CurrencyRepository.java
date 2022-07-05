package com.example.demo.repository;

import com.example.demo.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
    //select * from currency where name = ?
    CurrencyEntity findFirstByName(String name);
//    List<> findByName(String name);
}
