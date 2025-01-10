package com.example.shop.controller;

import com.example.shop.dto.request.ShopCreateRequestDto;
import com.example.shop.dto.request.ShopUpdateRequestDto;
import com.example.shop.dto.response.ShopReadResponseDto;
import com.example.shop.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.utils.Page;
import com.example.utils.PageQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // 단건 가게 조회
    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopReadResponseDto> find(@PathVariable int id) {
        return ResponseEntity.ok(shopService.find(id));
    }

    // 모든 가게 조회 (페이징 처리)
    @GetMapping("/shops")
    public ResponseEntity<Page<ShopReadResponseDto>> findAll(PageQuery pageQuery) {
        return ResponseEntity.ok(shopService.findAll(pageQuery));
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
        return ResponseEntity.ok(shopService.partialUpdate(id, dto));
    }

    // 가게 삭제 (논리적 삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        shopService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
