package com.fk.springboot.order.message;

import com.fk.springboot.order.dataobject.ProductInfoOutput;
import com.fk.springboot.order.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String msg) {
        //message 转换成 ProductInfoOutput 对象
        ProductInfoOutput productInfoOutput = (ProductInfoOutput) JsonUtil.fromJson(msg, ProductInfoOutput.class);
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutput);

        //存储到redis中
        stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                String.valueOf(productInfoOutput.getProductStock()));
    }
}
