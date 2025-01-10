package com.example.shop.repository;

import com.example.shop.entity.Shop;
import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    // 삭제되지 않은 모든 가게 조회
    @Query("SELECT s FROM Shop s WHERE s.isDeleted = false")
    List<Shop> findAllActive();

    // 받은 ID로 삭제되지 않은 가게 조회
    @Query("SELECT s FROM Shop s WHERE s.id = :id AND s.isDeleted = false")
    Shop findActiveById(int id);

    // 이름에 해당하는 삭제되지 않은 가게 조회
    @Query("SELECT s FROM Shop s WHERE s.name LIKE %:name% AND s.isDeleted = false")
    List<Shop> findByNameContainingAndIsDeletedFalse(String name);

    // 사용자가 생성한 삭제되지 않은 가게 수 조회
    long countByUserAndIsDeletedFalse(User user);
}
