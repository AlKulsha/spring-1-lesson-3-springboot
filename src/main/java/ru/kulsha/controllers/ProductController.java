package ru.kulsha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.dto.ProductDto;
import ru.kulsha.exceptions.AppError;
import ru.kulsha.exceptions.ResourceNotFoundException;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;
import ru.kulsha.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Page<ProductDto> getAllProducts(
            @RequestParam(name="p", defaultValue = "1") Integer page,
            @RequestParam(name="min_price", required = false) Integer minPrice,
            @RequestParam(name="max_price", required = false) Integer maxPrice,
            @RequestParam(name="title_part", required = false) String titlePart
    ){
        if(page < 1){
            page = 1;
        }
        return productService.find(minPrice, maxPrice, titlePart, page).map(
                p -> new ProductDto(p)
        );
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product is not found, id: " + id));
    }

    @PostMapping()
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto){
        productService.save(new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice()));
        return productDto;
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        Product product = productService.findById(productDto.getId()).get();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
