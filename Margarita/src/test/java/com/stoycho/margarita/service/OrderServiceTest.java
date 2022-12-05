package com.stoycho.margarita.service;

import com.stoycho.margarita.DTO.OrderDTO;
import com.stoycho.margarita.enums.OrderStatus;
import com.stoycho.margarita.enums.ProductType;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.*;
import com.stoycho.margarita.repository.IOrderRepository;
import com.stoycho.margarita.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private  IOrderRepository iOrderRepository;
    @Mock
    private  IUserRepository iUserRepository;
    private OrderService underTest;

    @BeforeEach
    void setUp() {
        underTest=new OrderService(iOrderRepository, iUserRepository);
    }

    @Test
    void willThrowWhenUserNotFoundInCreateOrder() {
        //Given
        String username="uname";
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.CreateOrder(username))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The user is not found in the database");

        verify(iOrderRepository, never()).save(any());
    }
    @Test
    void willThrowWhenUserHaveEmptyCartInCreateOrder() {
        //Given
        String username="uname";
        User user=new User(
                772L,
                "fname lname",
                username,
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "12345678",
                new ArrayList<>(),
                new ShoppingCard(10L,new ArrayList<>(),null));
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.of(user));
        //When
        //Then
        assertThatThrownBy(() ->underTest.CreateOrder(username))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("There are no products in your basket");

        verify(iOrderRepository, never()).save(any());
    }
    @Test
    void CreateOrder() {
        //Given
        Product product1=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        Product product2=new Product(5L,"chocolate cake","It is good",40, ProductType.SPECIAL,"");
        String username="uname";
        OrderItem item1 = new OrderItem(1L, product1, 2);

        OrderItem item2 = new OrderItem(2L, product2, 3);
        User user=new User(
                772L,
                "fname lname",
                "uname",
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "12345678",
                new ArrayList<>(),
                new ShoppingCard(10L,new ArrayList<>(),null));
        user.getShoppingCard().getItems().add(item1);
        user.getShoppingCard().getItems().add(item2);
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.of(user));
        //When
        Order theOrder=underTest.CreateOrder(username);

        //Then
        assertThat(theOrder.getItems().get(0)).isEqualTo(item1);
        assertThat(theOrder.getItems().get(1)).isEqualTo(item2);
        assertThat(theOrder.getTotal()).isEqualTo(84+120);
        assertThat(theOrder.getStatus()).isEqualTo(OrderStatus.NEW);
    }

    @Test
    void willThrowWhenUserNotFoundInChangeStatus() {
        //Given
        Long orderId=5L;
        OrderStatus status=OrderStatus.NEW;
        given(iOrderRepository.findById(orderId))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.ChangeStatus(orderId,status))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The order is not found in the database");
    }
    @Test
    void changeStatus() {
        //Given
        Order order= new Order(7L,"671932", LocalDate.now(),0,OrderStatus.NEW,new ArrayList<>(),null);
        OrderStatus newStatus=OrderStatus.CANCELED;
        given(iOrderRepository.findById(order.getOrder_id()))
                .willReturn(Optional.of(order));

        //When
        Order captured=underTest.ChangeStatus(order.getOrder_id(),newStatus);

        //Then
        assertThat(captured.getStatus()).isEqualTo(newStatus);
    }

    @Test
    void getAllOrders() {
        //Given
        Integer pageNumber=1;
        PageRequest expected=PageRequest.of(pageNumber, 5, Sort.by(Sort.Direction.ASC, "date"));
        //When
        underTest.GetAllOrders(pageNumber);

        //Then
        ArgumentCaptor<PageRequest> pageNumberArgumentCaptor=
                ArgumentCaptor.forClass(PageRequest.class);

        verify(iOrderRepository).findAll(pageNumberArgumentCaptor.capture());

        PageRequest capturedPage = pageNumberArgumentCaptor.getValue();

        assertThat(capturedPage).isEqualTo(expected);
    }

    @Test
    void getAllNewOrders() {
        Product product1=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        Product product2=new Product(5L,"chocolate cake","It is good",40, ProductType.SPECIAL,"");
        OrderItem item1 = new OrderItem(1L, product1, 2);
        OrderItem item2 = new OrderItem(2L, product2, 3);
        User user=new User(
                772L,
                "fname lname",
                "uname",
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "123456",
                new ArrayList<>(),
                new ShoppingCard(10L,null,null));
        Order order1= new Order(6L,"671932", LocalDate.now(),0,OrderStatus.NEW,new ArrayList<>(),user);
        order1.getItems().add(item1);
        order1.getItems().add(item2);
        Order order2= new Order(7L,"671934", LocalDate.now(),0,OrderStatus.NEW,new ArrayList<>(),user);
        order2.getItems().add(item1);
        List<Order> orders=new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        List <OrderDTO> orderDTOS=new ArrayList<>();
        for (Order o:orders) {
            orderDTOS.add(new OrderDTO(o));
        }
        given(iOrderRepository.findAllByStatus_Equals(OrderStatus.NEW))
                .willReturn(orders);

        //When
        List<OrderDTO> captured=underTest.GetAllNewOrders();

        //Then
        assertThat(captured).isEqualTo(orderDTOS);
    }
}