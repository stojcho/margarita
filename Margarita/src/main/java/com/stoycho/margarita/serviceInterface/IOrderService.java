package com.stoycho.margarita.serviceInterface;

import com.stoycho.margarita.DTO.OrderDTO;
import com.stoycho.margarita.enums.OrderStatus;
import com.stoycho.margarita.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    Order CreateOrder(String username);
    Order ChangeStatus(Long orderId, OrderStatus status);
    Page<Order> GetAllOrders(int pageNumber);
    List<OrderDTO> GetAllNewOrders();
}
