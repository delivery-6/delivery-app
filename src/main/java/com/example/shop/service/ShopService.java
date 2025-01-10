package com.example.shop.service;

import com.example.shop.dto.request.ShopCreateRequestDto;
import com.example.shop.dto.request.ShopUpdateRequestDto;
import com.example.shop.dto.response.ShopReadResponseDto;
import com.example.shop.entity.Shop;
import com.example.shop.repository.ShopRepository;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import com.example.utils.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.utils.Page;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    // 가게 조회 (삭제되지 않은 데이터만)
    public ShopReadResponseDto find(int id) {
        Shop shop = shopRepository.findActiveById(id);
        if (shop == null) {
            throw new IllegalArgumentException("'" + id + "' 가게를 찾을 수 없습니다.");
        }
        return ShopReadResponseDto.from(shop);
    }

    // 모든 가게 조회 (삭제되지 않은 데이터만)
    public Page<ShopReadResponseDto> findAll(PageQuery page, String name) {
        return Page.from(
            shopRepository.findByNameContainingAndIsDeletedFalse(page.toPageable(), name)
                    .map(ShopReadResponseDto::from)
        );
    }

    // 가게 생성
    public ShopReadResponseDto create(ShopCreateRequestDto dto) {
        // 인증된 사용자 가져오기
        User user = userRepository.findById(AuthUtil.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다." + AuthUtil.getId()));

        // User 의 role 을 확인하여 OWNER 권한이 있는지 체크
        if (user.getRole() != Role.OWNER) {
            throw new IllegalArgumentException("가게를 생성할 권한이 없습니다.");
        }

        long shopCount = shopRepository.countByUserAndIsDeletedFalse(user);
        if (shopCount >= 3) {
            throw new IllegalArgumentException("최대 3개의 상점만 만들 수 있습니다.");
        }

        // 가게 생성
        Shop shop = new Shop(user, dto.name(), dto.openedAt(), dto.closedAt(), dto.minOrderPrice());
        shop = shopRepository.save(shop);

        return ShopReadResponseDto.from(shop);
    }

    // 부분 업데이트
    public ShopReadResponseDto partialUpdate(int id, ShopUpdateRequestDto dto) {
        Shop shop = shopRepository.findActiveById(id);
        if (shop == null) {
            throw new IllegalArgumentException("'" + id + "' 가게를 찾을 수 없습니다.");
        }

        shop.updateDetails(dto.name(), dto.openedAt(), dto.closedAt(), dto.minOrderPrice());
        shop = shopRepository.save(shop);
        return ShopReadResponseDto.from(shop);
    }

    // 가게 삭제 (논리적 삭제)
    public void delete(int id) {
        Shop shop = shopRepository.findActiveById(id);
        if (shop == null) {
            throw new IllegalArgumentException("ID '" + id + "'에 해당하는 가게를 찾을 수 없습니다.");
        }

        String shopName = shop.getName();  // 가게 이름 가져오기
        shop.markAsDeleted();
        shopRepository.save(shop);

        System.out.println("가게 '" + shopName + "' (ID: " + id + ")이 성공적으로 삭제 처리되었습니다.");
    }
}
