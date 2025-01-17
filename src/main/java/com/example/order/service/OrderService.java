package com.example.order.service;

import com.example.exception.CustomException;
import com.example.exception.ErrorCode;
import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import com.example.order.dto.request.OrderCreateRequestDto;
import com.example.order.dto.request.OrderUpdateRequestDto;
import com.example.order.dto.response.OrderResponseDto;
import com.example.order.entity.Order;
import com.example.order.entity.OrderState;
import com.example.order.repository.OrderRepository;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import com.example.utils.Page;
import com.example.utils.PageQuery;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;

    public OrderResponseDto find(int id) {
        Order order = orderRepository.findByIdWithMenus(id)
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "Cannot found Order with ID: " + id));
        return OrderResponseDto.from(order);
    }

    public Page<OrderResponseDto> findAll(PageQuery pageQuery) {
       return Page.from(orderRepository.findAllByUserId(pageQuery.toPageable(), getAuthUser().getId())
                .map(OrderResponseDto::from));

    }

    public OrderResponseDto create(OrderCreateRequestDto dto) {
        // DTO 에 있는 메뉴와 갯수 묶음을 Map<Menu, int> 형태로 변환합니다.
        Map<Menu, Integer> menuInfo = new HashMap<>();
        for (var keyValue : dto.menuInfo().entrySet()) {
            Menu menu = menuRepository.findById(keyValue.getKey())
                    .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "Cannot found Menu with ID: " + keyValue.getKey()));
            menuInfo.put(menu, keyValue.getValue());
        }

        Order order = Order.from(menuInfo, getAuthUser());
        order = orderRepository.save(order);
        return OrderResponseDto.from(order);
    }

    public OrderResponseDto update(int id, OrderUpdateRequestDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "Cannot found Order with ID: " + id));

        order.updateState(OrderState.of(dto.status()));
        order = orderRepository.save(order);
        return OrderResponseDto.from(order);
    }

    private User getAuthUser() {
        return userRepository.findById(AuthUtil.getId())
                .orElseThrow(() -> CustomException.of(ErrorCode.NOT_FOUND, "User not found with ID: " + AuthUtil.getId()));
    }
}
