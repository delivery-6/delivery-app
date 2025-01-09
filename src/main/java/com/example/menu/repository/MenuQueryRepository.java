package com.example.menu.repository;

import com.example.menu.entity.Menu;

import java.util.List;

interface MenuQueryRepository {
    List<Menu> findAllByShopId(int id);
}
