package com.example.order.controller;

import com.example.order.dto.request.OrderCreateRequestDto;
import com.example.order.dto.request.OrderUpdateRequestDto;
import com.example.order.dto.response.OrderResponseDto;
import com.example.order.service.OrderService;
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
    public ResponseEntity<List<OrderResponseDto>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
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


/**
 * 1. 주문내역도 주문도메인안에서 같이 처리해도 되는지
 *          A: 걍 order 를 주문내역이랑 똑같이 써버려도 되는데 왜 그렇게 함
 *          ?: order, menu 가 데이터 엄청 겹치지않을까
 *          A: 알빠노
 * 2. 주문 컨트롤러 가게 입장과 고객 입장을 나눠서 구현해야되나
 *          A: 필수기능은 고객입장임
 */