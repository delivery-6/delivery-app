package com.example.menu.controller;

import com.example.menu.dto.request.MenuCreateRequestDto;
import com.example.menu.dto.request.MenuUpdateRequestDto;
import com.example.menu.dto.response.MenuResponseDetailDto;
import com.example.menu.dto.response.MenuResponseSimpleDto;
import com.example.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/{shopId}/menu/")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("{id}")
    public MenuResponseDetailDto find(int id){
        return menuService.find(id);
    }

    @GetMapping
    public List<MenuResponseSimpleDto> findAll(int shopId){
        return menuService.findAll(shopId);
    }

    @PostMapping
    public MenuResponseDetailDto create(int shopId, MenuCreateRequestDto dto){
        return menuService.create(shopId, dto);
    }

    @PutMapping("{id}")
    public MenuResponseDetailDto update(int id, MenuUpdateRequestDto dto){
        return menuService.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(int id){
        menuService.delete(id);
    }
}
