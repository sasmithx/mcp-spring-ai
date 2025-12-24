package com.sasmithx.mcp;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private List<Product> products = new ArrayList<>();

    @Tool(name = "getProducts", description = "Get all products")
    public List<Product> getProducts() {
        LOGGER.info("Fetching all products");
        return products;
    }

    @Tool(name = "getProduct", description = "Get product by name")
    public Product getProduct(String name) {
        LOGGER.info("Fetching product with name: {}", name);
        return products.stream()
                .filter(product -> product.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @PostConstruct
    public void init() {
        products.addAll(List.of(
                new Product("Mac Book Pro M4", "https://support.apple.com/en-lk/121552"),
                new Product("Samsung S25 Ultra", "https://www.gsmarena.com/samsung_galaxy_s25_ultra-13322.php")
        ));
    }
}
