package com.example.menu.repository;

import com.example.menu.entity.Menu;
import com.example.menu.entity.QMenu;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.shop.entity.QShop.shop;
import static com.example.menu.entity.QMenu.menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuQueryRepository {
    Menu findByName(String name);
}

interface MenuQueryRepository {
    List<Menu> findAllByShopId(int id);
}

@Repository
class MenuQueryRepositoryImpl implements MenuQueryRepository {
    private final JPQLQueryFactory queryFactory;

    public MenuQueryRepositoryImpl(JPQLQueryFactory queryFactory) {
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