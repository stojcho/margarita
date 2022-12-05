package com.stoycho.margarita.service;

import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.form.ShoppingCartForm;
import com.stoycho.margarita.model.DashboardOrder;
import com.stoycho.margarita.model.DashboardProduct;
import com.stoycho.margarita.model.OrderItem;
import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.repository.IOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)

class DashboardServiceTest {

    @Mock
    private  IOrderRepository iOrderRepository;
    private DashboardService underTest;

    @BeforeEach
    void setUp() {
        underTest=new DashboardService(iOrderRepository);
    }
    @Test
    void getDashboardDataProducts() {
        //Given
        List<DashboardProduct> expected=new ArrayList<>();
        expected.add(new DashboardProduct("Cake",23L));
        expected.add(new DashboardProduct("Chocolate",22L));
        given(iOrderRepository.getAllProductOrdersForGraph())
                .willReturn(expected);
        //When
        List<DashboardProduct> received=underTest.getDashboardDataProducts();
        //Then
        assertThat(expected).isEqualTo(received);
    }
    @Test
    void willThrowWhenUserNotFoundInGetDashboardDataProducts() {
        //Given
        given(iOrderRepository.getAllProductOrdersForGraph())
                .willReturn(new ArrayList<>());
        //When
        //Then
        assertThatThrownBy(() ->underTest.getDashboardDataProducts())
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("There are no orders found in the database");
    }


    @Test
    void getDashboardDataOrders() {
        //Given
        List<DashboardOrder> expected=new ArrayList<>();
        expected.add(new DashboardOrder(LocalDate.now(),23L));
        expected.add(new DashboardOrder(LocalDate.now().minusDays(1),22L));
        given(iOrderRepository.getOrdersByDateForGraph())
                .willReturn(expected);
        //When
        List<DashboardOrder> received=underTest.getDashboardDataOrders();
        //Then
        assertThat(expected).isEqualTo(received);
    }
    @Test
    void willThrowWhenUserNotFoundInGetDashboardDataOrders() {
        //Given
        given(iOrderRepository.getOrdersByDateForGraph())
                .willReturn(new ArrayList<>());
        //When
        //Then
        assertThatThrownBy(() ->underTest.getDashboardDataOrders())
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("There are no orders found in the database");
    }
}