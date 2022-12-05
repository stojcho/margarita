package com.stoycho.margarita.controller;

import com.stoycho.margarita.DTO.OrderDTO;
import com.stoycho.margarita.DTO.OrderNumberDTO;
import com.stoycho.margarita.form.OrderChangeStatusForm;
import com.stoycho.margarita.model.Order;
import com.stoycho.margarita.serviceInterface.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user/order")
public class OrderController {

    IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderNumberDTO> createOrder(@RequestParam String username) {
        OrderNumberDTO order=new OrderNumberDTO(orderService.CreateOrder(username));

        return  ResponseEntity.ok().body(order);
    }

    @PutMapping("/update/status")
    public ResponseEntity<List<OrderDTO>> changeStatus(@RequestBody OrderChangeStatusForm form) {
        orderService.ChangeStatus(form.getOrderId(),form.getStatus());
        List<OrderDTO> orders = orderService.GetAllNewOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Order>> getOrders(@RequestParam int pageNumber) {
        Page<Order> orders = orderService.GetAllOrders(pageNumber);
        if(orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok().body(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/new")
    public ResponseEntity<List<OrderDTO>> getNewOrders() {
        List<OrderDTO> orders = orderService.GetAllNewOrders();
        if(orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok().body(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}