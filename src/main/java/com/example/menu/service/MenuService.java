package com.example.menu.service;

import com.example.menu.dto.request.MenuCreateRequestDto;
import com.example.menu.dto.request.MenuUpdateRequestDto;
import com.example.menu.dto.response.MenuResponseDetailDto;
import com.example.menu.dto.response.MenuResponseSimpleDto;
import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import com.example.shop.entity.Shop;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;

    //TODO: GlobalExceptionHandler 구현 후, 에러핸들링 구현 예정입니다.
    private final User authUser = userRepository.findById(AuthUtil.getId())
            .orElseThrow(() ->
                    new NullPointerException("User not found with ID: " + AuthUtil.getId())
            );

    public MenuResponseDetailDto find(int id) {
        //TODO: GlobalExceptionHandler 구현 후, 에러핸들링 구현 예정입니다.
        Menu menu = menuRepository.findById(id).orElseThrow();

        return MenuResponseDetailDto.from(menu);
    }

    public List<MenuResponseSimpleDto> findAll(int shopId) {
        List<Menu> menus = menuRepository.findAllByShopId(shopId);
        return menus.stream()
                .map(MenuResponseSimpleDto::from)
                .toList();
    }

    public MenuResponseDetailDto create(
            int shopId,
            MenuCreateRequestDto dto
    ) {
        /**
         * TODO: ShopRepository 가 완성 후, 수정해야합니다.
         * TODO: GlobalExceptionHandler 구현 후, 에러핸들링 구현 예정입니다.
         * Shop shop = shopRepository.findById(dto.shopId).orElseThrow();
         **/
        Shop shop = new Shop();
        if (shop.getUser().getId() != authUser.getId()) {
            throw new IllegalArgumentException(
                    "Only the owner of the shop '" + shop.getName() + "' is allowed to add menus."
            );
        }

        Menu menu = menuRepository.save(
                Menu.from(shop, dto.name(), dto.price())
        );
        return MenuResponseDetailDto.from(menu);
    }

    public MenuResponseDetailDto update(
            int menuId,
            MenuUpdateRequestDto dto
    ) {
        /**
         * TODO: GlobalExceptionHandler 구현 후, 에러핸들링 구현 예정입니다.
         * Shop shop = shopRepository.findById(dto.shopId).orElseThrow();
         **/
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        if (menu.getShop().getUser().getId() != authUser.getId()) {
            throw new IllegalArgumentException(
                    "Only the owner of the shop '" + menu.getShop().getName() + "' is allowed to update menu."
            );
        }
        menu = menuRepository.save(menu.partialUpdate(dto));
        return MenuResponseDetailDto.from(menu);
    }

    public void delete(int menuId) {
        menuRepository.deleteById(menuId);
    }
}
