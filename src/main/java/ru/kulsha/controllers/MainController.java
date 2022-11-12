package ru.kulsha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kulsha.model.Product;
import ru.kulsha.repository.ProductRepository;

import java.util.List;

@Controller
public class MainController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product/{id}/info")
    @ResponseBody
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
}
