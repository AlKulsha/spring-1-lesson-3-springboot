package ru.kulsha.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kulsha.dto.ProductDto;
import ru.kulsha.exceptions.ResourceNotFoundException;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;
import ru.kulsha.repository.specifications.ProductsSpecifications;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    //final для полей, которые хотим исп. в конструкторе
    private final ProductRepository productRepository;


    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 50 ));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    @Transactional
    public void changePrice(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("You can`t change the price, id: " + productId));
        product.setPrice(product.getPrice() + delta);
        productRepository.save(product);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
    @Transactional
    public Product update(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("You can`t update the product. The product is not found. Id - " + productDto.getId()));
                product.setTitle(productDto.getTitle());
                product.setPrice(productDto.getPrice());
        return product;
    }


}
