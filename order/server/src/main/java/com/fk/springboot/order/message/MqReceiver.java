package com.fk.springboot.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {

    /**
     * 第一种方式：
     *   手动在RabbitMq中创建queue的名称，然后再接收端使用这个注解形式应用
     *   @RabbitListener(queues = "fkQueue")。
     *   缺点：需要手动创建，不利于维护。也不是自动化的。
     *   @RabbitListener(queues = "fkQueue")
     *   public void process(String message) {
     *         log.info("MqReceiver 接收到信息：{}", message);
     *     }
     * */

    /**
     * 第二种方式：
     * 自动创建队列，使用queuesTODeclare定义名称
     * @RabbitListener(queuesTODeclare = @Queue("fkQueue"))
     * public void process(String message) {
     *     log.info("MqReceiver 接收到信息：{}", message);
     * }
     *
     * */
    /**
     * 第三种方式：也是自动创建
     * 将queue和exchange绑定，使用绑定关键字
     * @RabbitListener(bindings = @QueueBinding(
     *             value = @Queue("fkQueue"),
     *             exchange = @Exchange("fkExchange")
     *     ))
     * public void process(String message) {
     *     log.info("MqReceiver 接收到信息：{}", message);
     * }
     * 这种方式适合于单独一个服务的方式，如果系统由多种服务接口时，就需要第四种方式了
     * */

    /**
     *  第四种方式：在某些特定的场景下，比如商城系统中：订单分为不同的种类，比如有些是数码产品，
     *      有些是生鲜产品，这些不同的产品有不同的业务系统/团队单独处理，这时需要区分他们从不
     *      同的业务到特殊指定的地方去，需要对消息进行分组。因此需要添加关键字来区分这些不同的数据进出。
     *      这时如何分组呢？通过key来标识，并指定不同的exchange和queue。
     * */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("fkComputerOrderExchange"),
            key = "computer",
            value = @Queue("fkComputerOrderQueue")
    ))
    public void processComputer(String message) {
        log.info("MqReceiver 接收computer信息：{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("fkFruitOrderExchange"),
            key = "fruit",
            value = @Queue("fkFruitOrderQueue")
    ))
    public void processFruit(String message) {
        log.info("MqReceiver 接收fruit信息：{}", message);
    }
}
