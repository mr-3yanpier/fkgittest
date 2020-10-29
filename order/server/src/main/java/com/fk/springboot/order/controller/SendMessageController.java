package com.fk.springboot.order.controller;

import com.fk.springboot.order.dto.OrderDTO;
import com.fk.springboot.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    /**
     * 传递类型为String
     */
    @GetMapping("/sendStreamMessage")
    public void process(){
        String msg = "这一段消息由客户端通过stream发送出来，时间戳：" + new Date();
        streamClient.sendOutput().send(MessageBuilder.withPayload(msg).build());
    }


    /**
     * 传递类型为Object时,OrderDTO
     */
    @GetMapping("/sendStreamObjMessage")
    public void processObject(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1111122222");
        orderDTO.setBuyerName("张三丰");
        orderDTO.setBuyerAddress("斯伯坦星球");
        streamClient.sendOutput().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
