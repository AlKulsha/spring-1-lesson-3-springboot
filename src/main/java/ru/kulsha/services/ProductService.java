package ru.kulsha.services;

import org.springframework.stereotype.Service;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void changePrice(Long productId, Integer delta) {
        Product product = productRepository.findById(productId);
        product.setPrice(product.getPrice() + delta);
    }
}
