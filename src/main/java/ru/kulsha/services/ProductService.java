package ru.kulsha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.kulsha.exceptions.ResourceNotFoundException;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

}
