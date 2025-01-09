package com.example.shop.service;

import com.example.shop.dto.request.ShopCreateRequestDto;
import com.example.shop.dto.request.ShopUpdateRequestDto;
import com.example.shop.dto.response.ShopReadResponseDto;
import com.example.shop.entity.Shop;
import com.example.shop.repository.ShopRepository;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new IllegalArgumentException("Shop not found with ID: " + id);
        }
        return ShopReadResponseDto.from(shop);
    }

    // 모든 가게 조회 (삭제되지 않은 데이터만)
    public List<ShopReadResponseDto> findAll(String name) {
        List<Shop> shops = shopRepository.findByNameContainingAndIsDeletedFalse(name);
        return shops.stream()
                .map(ShopReadResponseDto::from)
                .collect(Collectors.toList());
    }

    // 가게 생성
    public ShopReadResponseDto create(ShopCreateRequestDto dto) {
        // 인증된 사용자 가져오기
        User user = userRepository.findById(AuthUtil.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + AuthUtil.getId()));

        // is_owner 여부 확인 및 가게 개수 제한
        if (!user.getIsOwner()) {
            throw new IllegalArgumentException("You are not authorized to create a shop.");
        }

        long shopCount = shopRepository.countByUserAndIsDeletedFalse(user);
        if (shopCount >= 3) {
            throw new IllegalArgumentException("You can only create up to 3 shops.");
        }

        // 가게 생성
        Shop shop = new Shop(user, dto.name(), dto.openingHours(), dto.closingHours(), dto.minOrderAmount());
        shop = shopRepository.save(shop);

        return ShopReadResponseDto.from(shop);
    }

    // 부분 업데이트
    public ShopReadResponseDto partialUpdate(int id, ShopUpdateRequestDto dto) {
        Shop shop = shopRepository.findActiveById(id);
        if (shop == null) {
            throw new IllegalArgumentException("Shop not found with ID: " + id);
        }

        shop.updateDetails(dto.name(), dto.openingHours(), dto.closingHours(), dto.minOrderAmount());
        shop = shopRepository.save(shop);
        return ShopReadResponseDto.from(shop);
    }

    // 가게 삭제 (논리적 삭제)
    public void delete(int id) {
        Shop shop = shopRepository.findActiveById(id);
        if (shop == null) {
            throw new IllegalArgumentException("Shop not found with ID: " + id);
        }

        shop.markAsDeleted();
        shopRepository.save(shop);
    }
}
