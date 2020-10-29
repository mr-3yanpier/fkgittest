package com.fk.springboot.order.services;

import com.fk.springboot.order.dataobject.OrderDetail;
import com.fk.springboot.order.dataobject.OrderMaster;
import com.fk.springboot.order.dto.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
}
