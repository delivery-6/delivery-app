package com.example.shop.controller;

import com.example.shop.dto.request.ShopCreateRequestDto;
import com.example.shop.dto.request.ShopUpdateRequestDto;
import com.example.shop.dto.response.ShopReadResponseDto;
import com.example.shop.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // 가게 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ShopReadResponseDto> find(@PathVariable int id) {
        // 주어진 ID로 가게 조회 후 반환
        return ResponseEntity.ok(shopService.find(id));
    }

    // 모든 가게 조회 (이름으로 필터링 가능)
    @GetMapping
    public ResponseEntity<List<ShopReadResponseDto>> findAll(@RequestParam(required = false) String name) {
        // 이름에 해당하는 가게들만 조회 (이름이 null 이면 모든 가게 조회)
        return ResponseEntity.ok(shopService.findAll(name));
    }

    // 가게 생성
    @PostMapping
    public ResponseEntity<ShopReadResponseDto> create(@RequestBody ShopCreateRequestDto dto) {
        return ResponseEntity.ok(shopService.create(dto));
    }

    // 가게 정보 업데이트
    @PatchMapping("/{id}")
    public ResponseEntity<ShopReadResponseDto> partialUpdate(
            @PathVariable int id,
            @Valid @RequestBody ShopUpdateRequestDto dto
    ) {
        try {
            ShopReadResponseDto updatedShop = shopService.partialUpdate(id, dto);
            return ResponseEntity.ok(updatedShop);
        } catch (Exception e) {
            // 예외 처리 (예: 가게를 찾을 수 없을 경우)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 가게 삭제 (논리적 삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        shopService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
