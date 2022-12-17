package ru.kulsha.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.converters.ProductConverter;
import ru.kulsha.dto.ProductDto;
import ru.kulsha.exceptions.AppError;
import ru.kulsha.exceptions.ResourceNotFoundException;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;
import ru.kulsha.services.ProductService;
import ru.kulsha.validators.ProductValidator;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name="p", defaultValue = "1") Integer page,
            @RequestParam(name="min_price", required = false) Integer minPrice,
            @RequestParam(name="max_price", required = false) Integer maxPrice,
            @RequestParam(name="title_part", required = false) String titlePart
    ){
        if(page < 1){
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product =  productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product is not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
