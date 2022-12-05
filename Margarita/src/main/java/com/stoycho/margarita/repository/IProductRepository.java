package com.stoycho.margarita.repository;

import com.stoycho.margarita.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    Product save(Product product);
}
