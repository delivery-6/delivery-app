package com.example.order.service;

import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import com.example.order.dto.OrderCreateRequestDTO;
import com.example.order.dto.OrderResponseDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderMenu;
import com.example.order.repository.OrderRepository;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;

    private final User user = userRepository.findById(AuthUtil.getId())

    public OrderResponseDTO find(int id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return OrderResponseDTO.from(order);
    }

    public OrderResponseDTO create(OrderCreateRequestDTO dto) {
        Order.from(dto.menuInfo(), )
        OrderMenu orderMenu = new OrderMenu();
        return OrderResponseDTO.from();
    }
}
