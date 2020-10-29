package com.fk.springboot.order.controller;

import com.fk.springboot.order.converter.OrderForm2OrderDTO;
import com.fk.springboot.order.dto.OrderDTO;
import com.fk.springboot.order.enums.ResultEnum;
import com.fk.springboot.order.exception.OrderException;
import com.fk.springboot.order.form.OrderForm;
import com.fk.springboot.order.services.OrderService;
import com.fk.springboot.order.utils.ResultVOUtil;
import com.fk.springboot.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //orderForm -> orderDto

        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】，购物车为空！");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO od = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", od.getOrderId());
        return ResultVOUtil.success(map);
    }
}
