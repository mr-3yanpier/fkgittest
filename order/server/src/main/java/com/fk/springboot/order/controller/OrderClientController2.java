package com.fk.springboot.order.controller;

import com.fk.springboot.order.client.ProductClient;
import com.fk.springboot.order.dataobject.ProductInfo_A;
import com.fk.springboot.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class OrderClientController2 {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/msg")
    public String getProductMsg(){
        String response = productClient.productMsg();
        log.info("response={}",response);

        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo_A> productInfoList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}",productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707", 2)));
        return "ok";
    }

}
