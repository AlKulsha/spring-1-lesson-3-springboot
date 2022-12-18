package ru.kulsha.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kulsha.converters.ProductConverter;
import ru.kulsha.exceptions.ResourceNotFoundException;
import ru.kulsha.model.Cart;
import ru.kulsha.model.Product;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductService productService;
    private final ProductConverter productConverter;

    public void addProduct(Long id){
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found"));
        cart.addProduct(productConverter.entityToDto(product));
    }

    public Cart showCart(){
        return cart.showCart();
    }

    public void deleteById(Long id){
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found"));
        cart.deleteProduct(productConverter.entityToDto(product));
    }

}
