package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.user.entity.User;

import java.util.List;

public interface ShopRepositoryCustom {
    List<Shop> findAllActive();
    Shop findActiveById(int id);
    List<Shop> findByNameContainingAndIsDeletedFalse(String name);
    long countByUserAndIsDeletedFalse(User user);
}
