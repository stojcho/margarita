package com.stoycho.margarita.service;

import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.Product;
import com.stoycho.margarita.repository.IProductRepository;
import com.stoycho.margarita.serviceInterface.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductService implements IProductService {

    private final IProductRepository iProductRepository;

    @Autowired
    public ProductService(IProductRepository iProductRepository)
    {
    this.iProductRepository = iProductRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        if(Objects.equals(product.getName(), "") || Objects.equals(product.getDescription(), "")){
            throw new BadRequestException("The product contains empty values.");
        }else if(product.getPrice()<0.1){
            throw new BadRequestException("The product price is less than 0.1");
        }
        Product savedProduct=iProductRepository.save(product);
        savedProduct.setImageURL(savedProduct.getImageURL()
                .replace("toChange",savedProduct.getId().toString()));
        return savedProduct;
    }

    @Override
    public List<Product> getAllProduct() {
       return iProductRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> prod= iProductRepository.findById(id);
        return prod.orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        iProductRepository.deleteById(id);
    }
}
