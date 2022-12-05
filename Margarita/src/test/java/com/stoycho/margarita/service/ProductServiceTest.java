package com.stoycho.margarita.service;

import com.stoycho.margarita.enums.ProductType;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.OrderItem;
import com.stoycho.margarita.model.Product;
import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class ProductServiceTest {
    @Mock
    private  IProductRepository iProductRepository;
    private ProductService underTest;

    @BeforeEach
    void setUp() {
        underTest=new ProductService(iProductRepository);
    }
    @Test
    void willThrowWhenSaveEmptyProduct() {
        //Given
        Product product=new Product(1L,"Cake","Delicious", 4,ProductType.SPECIAL,"");
        //When
        //Then
        product.setName("");
        assertThatThrownBy(() ->underTest.saveProduct(product))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The product contains empty values.");

        product.setName("Cake");
        product.setDescription("");
        assertThatThrownBy(() ->underTest.saveProduct(product))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The product contains empty values.");

        product.setDescription("Delicious");
        product.setPrice(0);
        assertThatThrownBy(() ->underTest.saveProduct(product))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The product price is less than 0.1");

        verify(iProductRepository, never()).save(any());
    }
    @Test
    void saveProduct() {
        //Given
        Product product=new Product(32L,"Cake","Delicious", 4,ProductType.SPECIAL,"toChange.jpg");

        String changedUrl="32.jpg";
        given(iProductRepository.save(product))
                .willReturn(product);
        //When

        Product returnedProduct= underTest.saveProduct(product);
        //Then
        assertThat(returnedProduct.getImageURL()).isEqualTo(changedUrl);
    }

    @Test
    void getAllProduct() {
        //Given
        //When
        underTest.getAllProduct();
        //Then
        verify(iProductRepository).findAll();
    }

    @Test
    void getProduct() {
        //Given
        Product product=new Product(32L,"Cake","Delicious", 4,ProductType.SPECIAL,"toChange.jpg");

        Long prodId=32L;
        given(iProductRepository.findById(prodId))
                .willReturn(Optional.of(product));
        //When
        Product returnedProduct=underTest.getProduct(prodId);

        //Then
        verify(iProductRepository).findById(prodId);
        assertThat(returnedProduct).isEqualTo(product);
    }

    @Test
    void deleteProduct() {
        //Given
        Long prodId=32L;
        //When
        underTest.deleteProduct(prodId);
        //Then
        verify(iProductRepository).deleteById(prodId);
    }
}