package com.example.menu.repository;

import com.example.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuQueryRepository {
    Menu findByName(String name);
}