package ru.kulsha.validators;

import org.springframework.stereotype.Component;
import ru.kulsha.dto.ProductDto;
import ru.kulsha.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if(productDto.getPrice() < 1){
            errors.add("The price can`t be less than 1");
        }
        if (productDto.getTitle().isBlank()){
            errors.add("The title of the product can`t be empty");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
