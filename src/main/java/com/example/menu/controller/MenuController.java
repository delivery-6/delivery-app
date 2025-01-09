package com.example.menu.controller;

import com.example.menu.dto.request.MenuCreateRequestDto;
import com.example.menu.dto.request.MenuUpdateRequestDto;
import com.example.menu.dto.response.MenuResponseDetailDto;
import com.example.menu.dto.response.MenuResponseSimpleDto;
import com.example.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/{shopId}/menu/")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("{id}")
    public ResponseEntity<MenuResponseDetailDto> find(@PathVariable int id){
        return ResponseEntity.ok(menuService.find(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponseSimpleDto>> findAll(@PathVariable int shopId){
        return ResponseEntity.ok(menuService.findAll(shopId));
    }

    @PostMapping
    public ResponseEntity<MenuResponseDetailDto> create(
            @PathVariable int shopId,
            @RequestBody MenuCreateRequestDto dto
    ){
        return ResponseEntity.ok(menuService.create(shopId, dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuResponseDetailDto> update(
            @PathVariable int id,
            @RequestBody MenuUpdateRequestDto dto
    ){
        return ResponseEntity.ok(menuService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
