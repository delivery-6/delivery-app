package com.example.menu.repository;

import com.example.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface MenuQueryRepository {
    Page<Menu> findAllByShopId(Pageable pageable, int id);
}
