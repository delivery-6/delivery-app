package com.example.menu.service;

import com.example.menu.dto.request.MenuCreateRequestDto;
import com.example.menu.dto.request.MenuUpdateRequestDto;
import com.example.menu.dto.response.MenuResponseDetailDto;
import com.example.menu.dto.response.MenuResponseSimpleDto;
import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import com.example.shop.entity.Shop;
import com.example.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

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
         * User user = getAuthenticatedUser();
         *         if(!shop.getUser().getId().equals(user.getId())){
         *             throw new Exception();
         *         }
         **/
        Shop shop = new Shop();

        Menu menu = menuRepository.save(Menu.from(shop, dto));
        return MenuResponseDetailDto.from(menu);
    }

    public MenuResponseDetailDto update(
            int menuId,
            MenuUpdateRequestDto dto
    ) {
        /**
         * TODO: GlobalExceptionHandler 구현 후, 에러핸들링 구현 예정입니다.
         * if(!menu.getShop().getUser().getId().equals(getAuthenticatedUser().getId()))
         *      throw new Exception("");
         */
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        menu = menuRepository.save(menu.partialUpdate(dto));

        return MenuResponseDetailDto.from(menu);
    }

    public void delete(int menuId){
        menuRepository.deleteById(menuId);

    }

    //TODO: 인증 구현 후, 세션을 이용해 유저를 가져오는 작업을 추가해야 합니다.
    private User getAuthenticatedUser() {
        return null;
    }
}
