package com.fk.springboot.order.controller;

import com.fk.springboot.order.config.FKConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FkController {
    @Autowired
    FKConfig fkConfig;

    @GetMapping("/fk/fkinfo")
    public String getFkInfo(){
        return "Name: "+fkConfig.getName() + " Age:" + fkConfig.getAge();
    }

}
