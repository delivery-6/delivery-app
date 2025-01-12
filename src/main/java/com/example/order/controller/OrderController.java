package com.example.order.controller;

import com.example.order.dto.request.OrderCreateRequestDto;
import com.example.order.dto.request.OrderUpdateRequestDto;
import com.example.order.dto.response.OrderResponseDto;
import com.example.order.service.OrderService;
import com.example.utils.Page;
import com.example.utils.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> find(
            @PathVariable int id
    ){
        return ResponseEntity.ok(orderService.find(id));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> findAll(PageQuery pageQuery){
        return ResponseEntity.ok(orderService.findAll(pageQuery));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(
            @RequestBody OrderCreateRequestDto dto
    ){
        return ResponseEntity.ok(orderService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(
            @PathVariable int id,
            @RequestBody OrderUpdateRequestDto dto
    ){
        return ResponseEntity.ok(orderService.update(id, dto));
    }
}