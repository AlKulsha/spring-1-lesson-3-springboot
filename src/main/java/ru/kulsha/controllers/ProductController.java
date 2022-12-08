package ru.kulsha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.exceptions.AppError;
import ru.kulsha.exceptions.ResourceNotFoundException;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;
import ru.kulsha.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @PostMapping("/products")
    public Product saveNewProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product is not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/products/changePrice")
    public void changePrice(@PathVariable Long productId, @PathVariable Integer delta){
        productService.changePrice(productId, delta);
    }
}
