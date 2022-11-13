package ru.kulsha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;

import java.util.List;

@RestController
public class MainController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product/{id}/info")
    public String showProductInfo (@PathVariable Long id) {
        return "Product # " + id;
    }

    @GetMapping("/products")
    public String showProductsPage(Model model){
       model.addAttribute("products", productRepository.getAllProducts());
        return "products_page";
    }

    @GetMapping("/products/{id}")
    public String showProductPage(Model model, @PathVariable Long id){
        Product product = productRepository.findById(id);
        model.addAttribute("product", product );
        return "product_info_page";
    }

    @GetMapping("/show_form")
    public String showFormPage(){
        return "product_form";
    }

    @GetMapping("/add_product")
    public String addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int price){
        Product product = new Product(id, title, price);
        productRepository.add(product);
        return "redirect:/products";
    }
}
