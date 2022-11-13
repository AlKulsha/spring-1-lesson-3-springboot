package ru.kulsha.repository;

import org.springframework.stereotype.Component;
import ru.kulsha.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(List.of(
                new Product(1L, "Milk", 80),
                new Product(2L, "Jam", 150),
                new Product(3L, "Eggs", 120),
                new Product(4L, "Potato", 30),
                new Product(5L, "Juice", 200)

        ));
    }

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id){
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product is not found"));
    }

    public void add(Product product){
        products.add(product);
    }


}
