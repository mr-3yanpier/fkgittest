package com.fk.springboot.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 发送mq消息
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSenderTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("fkQueue","这一段内容从发送端发出-"+ new Date());
    }

    @Test
    public void sendComputerOrder(){
        amqpTemplate.convertAndSend("fkComputerOrderExchange","computer","这一段内容从发送端发出-"+ new Date());
    }

    @Test
    public void sendFruitOrder(){
        amqpTemplate.convertAndSend("fkFruitOrderExchange","fruit","这一段内容从发送端发出-"+ new Date());
    }

}
