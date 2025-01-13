package com.example.menu.repository;

import com.example.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuQueryRepository {
    Menu findByName(String name);

    List<Menu> findByShopId(int shopId);
}