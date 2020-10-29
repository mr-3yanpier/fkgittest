package com.fk.springboot.order.converter;

import com.fk.springboot.order.dataobject.OrderDetail;
import com.fk.springboot.order.dto.OrderDTO;
import com.fk.springboot.order.enums.ResultEnum;
import com.fk.springboot.order.exception.OrderException;
import com.fk.springboot.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return  orderDTO;
    }
}
