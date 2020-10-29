package com.fk.springboot.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    //统一使用这个INPUT定义关键字
    String sendINPUT = "fkStreamSendMessage";

    String replyINPUT = "fkStreamReplyMessage";

    //发送时的input
    @Input(StreamClient.sendINPUT)
    SubscribableChannel sendInput();
    //发送时的output
    @Output(StreamClient.sendINPUT)
    MessageChannel sendOutput();
    //接收回复时的input
    @Input(StreamClient.replyINPUT)
    SubscribableChannel replyInput();
    //接收回复时的output
    @Output(StreamClient.replyINPUT)
    MessageChannel replyOutput();
}
