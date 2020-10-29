package com.fk.springboot.order.message;

import com.fk.springboot.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    /**
     * 接收String消息
     * @param message
     */
//    @StreamListener(value = StreamClient.sendINPUT)
//    public void process(Object message){
//        log.info("StreamReceiver: {}", message);
//    }

    /**
     * 接收OrderDTO 对象消息
     * @param message
     */
    @StreamListener(value = StreamClient.sendINPUT)
    @SendTo(StreamClient.replyINPUT)
    public String processObject(OrderDTO message){
        log.info("StreamReceiver: {}", message);
        return "消费者已经处理完业务，现返回本条消息！";
    }

    /**
     * 接收OrderDTO 对象消息，并回复信息
     * @param message
     */
    @StreamListener(value = StreamClient.replyINPUT)
    public void processAndReply(String message){
        log.info("StreamReceiver2: {}", message);
    }

}
