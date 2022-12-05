package com.stoycho.margarita.serviceInterface;


import com.stoycho.margarita.model.Product;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product getProduct(Long id);
    void deleteProduct(Long id);
}
