package com.example.App.controllers;



import com.example.App.entity.Product;
import com.example.App.sevices.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return service.saveOrUpdate(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<Map<String, Object>> findProductsByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @PathVariable(value = "category") String category) {
        return service.findProductsByCategory(page, size, category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {
        return service.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
