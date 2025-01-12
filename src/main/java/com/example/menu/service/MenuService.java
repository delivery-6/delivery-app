package com.example.menu.service;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.menu.dto.request.MenuCreateRequestDto;
import com.example.menu.dto.request.MenuUpdateRequestDto;
import com.example.menu.dto.response.MenuResponseDetailDto;
import com.example.menu.dto.response.MenuResponseSimpleDto;
import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import com.example.shop.entity.Shop;
import com.example.shop.repository.ShopRepository;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import com.example.utils.Page;
import com.example.utils.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;

    public MenuResponseDetailDto find(int id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "Cannot found Menu with ID: " + id));

        return MenuResponseDetailDto.from(menu);
    }

    public Page<MenuResponseSimpleDto> findAll(PageQuery page, int shopId) {
        return Page.from(
                menuRepository.findAllByShopId(page.toPageable(), shopId)
                        .map(MenuResponseSimpleDto::from));
    }

    public MenuResponseDetailDto create(
            int shopId,
            MenuCreateRequestDto dto
    ) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "Cannot found Shop with ID: " + shopId));

        if (shop.getUser().getId() != getAuthUser().getId())
            throw CustomException.of(ErrorCode.UNAUTHORIZED,
                    "Only the owner of the shop '" + shop.getName() + "' is allowed to add menus.");

        Menu menu = menuRepository.save(
                Menu.of(shop, dto.name(), dto.price()));
        return MenuResponseDetailDto.from(menu);
    }

    public MenuResponseDetailDto update(
            int menuId,
            MenuUpdateRequestDto dto
    ) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "Cannot found Menu with ID: " + menuId));

        if (menu.getShop().getUser().getId() != getAuthUser().getId())
            throw CustomException.of(ErrorCode.UNAUTHORIZED,
                    "Only the owner of the shop '" + menu.getShop().getName() + "' is allowed to update menu.");

        menu = menuRepository.save(menu.partialUpdate(dto));
        return MenuResponseDetailDto.from(menu);
    }

    public void delete(int menuId) {
        menuRepository.deleteById(menuId);
    }

    private User getAuthUser() {
        return userRepository.findById(AuthUtil.getId())
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "User not found with ID: " + AuthUtil.getId()));
    }
}
