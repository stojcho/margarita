package com.stoycho.margarita.service;

import com.stoycho.margarita.DTO.OrderDTO;
import com.stoycho.margarita.enums.OrderStatus;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.Order;
import com.stoycho.margarita.model.OrderItem;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.repository.IOrderRepository;
import com.stoycho.margarita.repository.IUserRepository;
import com.stoycho.margarita.serviceInterface.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final IOrderRepository iOrderRepository;
    private final IUserRepository iUserRepository;

    @Transactional
    @Override
    public Order CreateOrder(String username){
        Optional<User> user = iUserRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new BadRequestException("The user is not found in the database");
        }
        List<OrderItem> orderItems = user.get().getShoppingCard().getItems();
        if(orderItems.isEmpty()){
            throw new BadRequestException("There are no products in your basket");
        }
        double total=0;
        for (OrderItem orderItem:orderItems) {
            total+=orderItem.getProduct().getPrice()* orderItem.getQuantity();
        }
        List<OrderItem> clonedList = new ArrayList<>(orderItems.size());
        for (OrderItem orderItem : orderItems) {
            clonedList.add(new OrderItem(orderItem));
        }
        Order newOrder= new Order(null,null, LocalDate.now(),total, OrderStatus.NEW,clonedList,user.get());
        user.get().getShoppingCard().getItems().clear();
        iOrderRepository.save(newOrder);
        return newOrder;
    }

    @Transactional
    @Override
    public Order ChangeStatus(Long orderId, OrderStatus status){
        Optional<Order> order=iOrderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new BadRequestException("The order is not found in the database");
        }
        order.get().setStatus(status);
        return order.get();
    }

    @Override
    public Page<Order> GetAllOrders(int pageNumber){
        return iOrderRepository.findAll(PageRequest.of(pageNumber, 5, Sort.by(Sort.Direction.ASC, "date")));
    }

    @Override
    public List<OrderDTO> GetAllNewOrders(){
        List<Order> orders = iOrderRepository.findAllByStatus_Equals(OrderStatus.NEW);
        List <OrderDTO> orderDTOS=new ArrayList<>();
        for (Order o:orders) {
            orderDTOS.add(new OrderDTO(o));
        }
        return orderDTOS;
    }
}
