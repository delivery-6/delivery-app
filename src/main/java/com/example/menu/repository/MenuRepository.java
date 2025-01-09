package com.example.menu.repository;

import com.example.menu.entity.Menu;

import com.example.utils.QuerydslUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuQueryRepository {
    Menu findByName(String name);
}

interface MenuQueryRepository {
    Page<Menu> findAllByShopId(Pageable pageable, int id);
}

@Repository
class MenuRepositoryImpl implements MenuQueryRepository {
    private final JPQLQueryFactory queryFactory;

    @Override
    public Page<Menu> findAllByShopId(Pageable pageable, int id) {
        var result = queryFactory
                .selectFrom(menu)
                .join(menu.shop, shop).fetchJoin()
                .where(menu.shop.id.eq(id));
        return QuerydslUtil.fetchPage(result, menu, pageable);
    }
}