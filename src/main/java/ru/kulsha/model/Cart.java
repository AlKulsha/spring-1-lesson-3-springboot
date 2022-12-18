package ru.kulsha.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.kulsha.dto.ProductDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
@Data
public class Cart {
    private List<ProductDto> productDtoList;
    private  int amount;

    @PostConstruct
    public void init(){
        productDtoList = new ArrayList<>();
    }

    public Cart() {
    }

    public void addProduct(ProductDto productDto){
        productDtoList.add(productDto);
        amount += 1;
    }

    public Cart showCart(){
        return this;
    }

    public void deleteProduct(ProductDto productDto){
        productDtoList.remove(productDto);
    }
}
