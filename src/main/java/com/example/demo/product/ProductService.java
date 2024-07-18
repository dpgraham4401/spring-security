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
        // for this example, we're asking the repository to return all products (regardless of the owner)
        // So we can see the pre-filtering in action
        return productRepository.findAllByOrderByName();
    }
}
