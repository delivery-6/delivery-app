package com.example.review.repository;

import com.example.menu.entity.Menu;

import java.util.List;

interface ReviewQueryRepository {
    List<Menu> findAllByShopId(int id);
}