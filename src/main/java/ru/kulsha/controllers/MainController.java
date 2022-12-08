package ru.kulsha.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kulsha.dto.ResultDto;


@RestController
public class MainController {

    @GetMapping("/calc/add")
    public ResultDto calculateAdd(@RequestParam Integer a, @RequestParam Integer b){
        return new ResultDto(a + b);
    }


}
