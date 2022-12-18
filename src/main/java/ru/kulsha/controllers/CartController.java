package ru.kulsha.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kulsha.model.Cart;
import ru.kulsha.services.CartService;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {

    private CartService cartService;

    @GetMapping("/add/{id}")
    public  void addProduct(@PathVariable Long id){
        cartService.addProduct(id);
    }

    @GetMapping
    public Cart showCart(){
        return cartService.showCart();
    }

    @GetMapping("/delete/{id}")
    public  void deleteProductById(@PathVariable Long id){
        cartService.deleteById(id);
    }



}
