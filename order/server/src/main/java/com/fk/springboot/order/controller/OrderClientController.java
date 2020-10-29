package com.fk.springboot.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderClientController {

    //供第二种方式使用
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //供第三种方式使用
//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        //1使用RestTemplate方式调用远程服务，服务器只有一个url提供服务时
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://192.168.0.161:8081/msg", String.class);

        //2 如果服务端有多个地址时，可以利用Spring自带的LoadBalanceClient获取负载后的地址，并组装url, 再使用RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");//PRODUCT是注册到eureka上的服务的名称
        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/msg";
        String response = restTemplate.getForObject(url, String.class);

        //3 使用@LoadBlance注解，直接在url中加入待调用的应用在eureka中注册的名字，再使用RestTemplate即可
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        log.info("response={}",response);
        return  "Message From OrderController: "+ response;

    }
}
