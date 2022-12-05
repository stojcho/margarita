package com.stoycho.margarita.controller;

import com.stoycho.margarita.model.Product;
import com.stoycho.margarita.serviceInterface.StorageService;
import com.stoycho.margarita.serviceInterface.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService iProductService;
    private final StorageService storageService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = null;
        products = iProductService.getAllProduct();
        if (products != null && !products.isEmpty()) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Product> saveProduct(@RequestParam("image") MultipartFile image,
                                               @RequestParam("name") String name,
                                               @RequestParam("category") String category,
                                               @RequestParam("description") String description,
                                               @RequestParam("price") Double price) throws IOException {

        //Check if file is not a jpg
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String[] parts= fileName.split("\\.");
        if(!Objects.equals(parts[parts.length-1], "jpg")){
            return ResponseEntity.badRequest().build();
        }

        Product product=new Product(name,description,price,category);
        storageService.store(image);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path("toChange."+parts[parts.length-1])
                .toUriString();
        product.setImageURL(uri);

        Product savedProduct= iProductService.saveProduct(product);
        storageService.changeFileName(fileName,savedProduct.getId().toString()+"."+parts[parts.length-1]);

        return ResponseEntity.ok().body(savedProduct);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id) {
        Product product = iProductService.getProduct(id);
        if(product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
