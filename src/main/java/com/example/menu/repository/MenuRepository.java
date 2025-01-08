package com.example.menu.repository;

import com.example.menu.entity.Menu;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.menu.entity.QMenu.menu;
import static com.example.shop.entity.QShop.shop;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuQueryRepository {
    Menu findByName(String name);
}

interface MenuQueryRepository {
    List<Menu> findAllByShopId(int id);
}

@Repository
class MenuRepositoryImpl implements MenuQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public MenuRepositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Menu> findAllByShopId(int id) {
        return queryFactory
                .selectFrom(menu)
                .join(menu.shop, shop).fetchJoin()
                .where(menu.shop.id.eq(id))
                .fetch();
    }
}