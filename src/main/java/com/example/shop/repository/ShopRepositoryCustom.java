package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopRepositoryCustom {
    Page<Shop> findAllActive(Pageable pageable);
    Shop findActiveById(int id);
    Page<Shop> findByNameContainingAndIsDeletedFalse(Pageable pageable, String name);
    long countByUserAndIsDeletedFalse(User user);
}
