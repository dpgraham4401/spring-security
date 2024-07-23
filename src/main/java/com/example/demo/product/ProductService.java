package com.example.demo.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Integer id) {
        return productRepository.getProductsById(id);
    }

    public List<Product> findSellingProducts() {
        return productRepository.findAllByOrderByName();
    }
}
