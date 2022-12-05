package com.stoycho.margarita.service;

import com.stoycho.margarita.enums.ProductType;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.form.ShoppingCartForm;
import com.stoycho.margarita.model.OrderItem;
import com.stoycho.margarita.model.Product;
import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ShoppingCardServiceTest {
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private IProductRepository iProductRepository;
    @Mock
    private IOrderItemRepository iOrderItemRepository;

    private ShoppingCardService underTest;

    @BeforeEach
    void setUp() {
        underTest=new ShoppingCardService(iUserRepository,iProductRepository,iOrderItemRepository);
    }


    @Test
    void willThrowWhenUserNotFoundInAddProduct() {
        //Given
        String username="uname";
        Long productId=5L;
        int quantity=2;
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.addProduct(username,productId,quantity))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The user is not found in the database");

        verify(iProductRepository, never()).findById(any());
    }
    @Test
    void willThrowWhenProductNotFoundInAddProduct() {
        //Given
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
                new ShoppingCard(10L,null,null));
        String username="uname";
        Long productId=5L;
        int quantity=2;
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.of(user));
        given(iProductRepository.findById(productId))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.addProduct(username,productId,quantity))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The product is not found in the database");
    }
    @Test
    void addProductWhenItemDoNotExist() {
        //Given
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
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        String username="uname";
        Long productId=5L;
        int quantity=2;
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.of(user));
        given(iProductRepository.findById(productId))
                .willReturn(Optional.of(product));
        //When

        ShoppingCard shoppingCard=underTest.addProduct(username,productId,quantity);
        //Then
        assertThat(shoppingCard.getItems().get(0)).isEqualTo(new OrderItem(null, product, quantity));
    }

    @Test
    void addProductWhenItemExists() {
        //Given
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        String username="uname";
        Long productId=5L;
        int quantity=2;
        OrderItem item = new OrderItem(null, product, quantity);

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
        user.getShoppingCard().getItems().add(item);
        given(iUserRepository.findByUsername(username))
                .willReturn(Optional.of(user));
        given(iProductRepository.findById(productId))
                .willReturn(Optional.of(product));
        //When

        ShoppingCard shoppingCard=underTest.addProduct(username,productId,quantity);
        //Then
        assertThat(shoppingCard.getItems().get(0)).isEqualTo(new OrderItem(null, product, quantity*2));
    }

    @Test
    void willThrowWhenUserNotFoundInRemoveOneProduct() {
        //Given
        ShoppingCartForm form=new ShoppingCartForm("uname",2L);
        given(iUserRepository.findByUsername(form.getUsername()))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.removeOneProduct(form))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The user is not found in the database");

        verify(iOrderItemRepository, never()).deleteById(any());
    }
    @Test
    void removeOneProductWhenItemEqualToOne(){
        //Given
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        ShoppingCartForm form=new ShoppingCartForm("uname",33L);
        int quantity=1;
        OrderItem item = new OrderItem(33L, product, quantity);

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
        user.getShoppingCard().getItems().add(item);
        given(iUserRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(user));
        //When

        underTest.removeOneProduct(form);
        //Then
        ArgumentCaptor<Long> userArgumentCaptor=
                ArgumentCaptor.forClass(Long.class);
        verify(iOrderItemRepository).deleteById(userArgumentCaptor.capture());

        Long capturedId = userArgumentCaptor.getValue();

        assertThat(capturedId).isEqualTo(item.getItem_id());
    }

    @Test
    void removeOneProductWhenMoreThanOnePresent(){
        //Given
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        ShoppingCartForm form=new ShoppingCartForm("uname",33L);
        int quantity=2;
        OrderItem item = new OrderItem(33L, product, quantity);

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
        user.getShoppingCard().getItems().add(item);
        given(iUserRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(user));
        //When

        ShoppingCard shoppingCard= underTest.removeOneProduct(form);
        //Then

        assertThat(shoppingCard.getItems().get(0).getQuantity()).isEqualTo(quantity-1);
    }
    @Test
    void willThrowWhenUserNotFoundInAddOneProduct() {
        //Given
        ShoppingCartForm form=new ShoppingCartForm("uname",2L);
        given(iUserRepository.findByUsername(form.getUsername()))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.addOneProduct(form))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The user is not found in the database");
    }
    @Test
    void addOneProductWhenQuantityEqualToOne(){
        //Given
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        ShoppingCartForm form=new ShoppingCartForm("uname",33L);
        int quantity=1;
        OrderItem item = new OrderItem(33L, product, quantity);

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
        user.getShoppingCard().getItems().add(item);
        given(iUserRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(user));
        //When
        ShoppingCard shoppingCard=underTest.addOneProduct(form);

        //Then
        assertThat(shoppingCard.getItems().get(0).getQuantity()).isEqualTo(quantity+1);
    }

    @Test
    void addOneProductWhenQuantityEqualTo20(){
        //Given
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        ShoppingCartForm form=new ShoppingCartForm("uname",33L);
        int quantity=20;
        OrderItem item = new OrderItem(33L, product, quantity);

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
        user.getShoppingCard().getItems().add(item);
        given(iUserRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(user));
        //When

        ShoppingCard shoppingCard= underTest.addOneProduct(form);
        //Then

        assertThat(shoppingCard.getItems().get(0).getQuantity()).isEqualTo(quantity);
    }
    @Test
    void willThrowWhenUserNotFoundInRemoveProduct() {
        //Given
        ShoppingCartForm form=new ShoppingCartForm("uname",2L);
        given(iUserRepository.findByUsername(form.getUsername()))
                .willReturn(Optional.empty());
        //When
        //Then
        assertThatThrownBy(() ->underTest.removeProduct(form))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The user is not found in the database");

        verify(iOrderItemRepository, never()).deleteById(any());
    }
    @Test
    void removeProduct(){
        //Given
        Product product=new Product(10L,"cake","nice",42, ProductType.BASIC,"");
        ShoppingCartForm form=new ShoppingCartForm("uname",33L);
        int quantity=20;
        OrderItem item = new OrderItem(33L, product, quantity);

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
        user.getShoppingCard().getItems().add(item);
        given(iUserRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(user));
        //When

        ShoppingCard shoppingCard= underTest.removeProduct(form);
        //Then

        assertThat(shoppingCard.getItems().isEmpty()).isTrue();
    }
}