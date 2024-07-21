package com.example.demo.product;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
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

    // Here we've botched the PostFilter to show that we can use custom attributes of our custom Principal object
    @PostFilter("'foobar' == authentication.principal.getCustomAttribute()")
    public List<Product> findSellingProducts() {
        // for this example, we're asking the repository to return all products (regardless of the owner)
        // So we can see the pre-filtering in action
        return productRepository.findAllByOrderByName();
    }


    // Here, we use the @PreFilter annotation to remove any product that does not have a matching owner
    // This is assuming that the input is coming from a trusted source.
    // If we have more than one parameter (collection), use the filterTarget attribute to specify the parameter to
    // filter
    @PreFilter(value = "filterObject.owner == authentication.name", filterTarget = "products")
    public void updatePrices(List<Product> products, List<Float> prices) {
        for (Product product : products) {
            product.setPrice(product.getPrice() * prices.get(products.indexOf(product)));
        }
    }
}
