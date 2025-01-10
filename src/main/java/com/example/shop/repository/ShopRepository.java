package com.example.shop.repository;

import com.example.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer>, ShopRepositoryCustom {
}
