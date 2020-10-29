package com.fk.springboot.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServerController {

    @GetMapping("/msg")
    public String getMsg(){
        return "From ProductServer: This is Product's message! ";
    }
}
