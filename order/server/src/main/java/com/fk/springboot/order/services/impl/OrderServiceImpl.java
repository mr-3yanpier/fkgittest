package com.fk.springboot.order.services.impl;

import com.fk.springboot.order.client.ProductClient;
import com.fk.springboot.order.dataobject.OrderDetail;
import com.fk.springboot.order.dataobject.OrderMaster;
import com.fk.springboot.order.dataobject.ProductInfo_A;
import com.fk.springboot.order.dto.CartDTO;
import com.fk.springboot.order.dto.OrderDTO;
import com.fk.springboot.order.enums.OrderStatusEnums;
import com.fk.springboot.order.enums.PayStatusEnum;
import com.fk.springboot.order.repository.OrderDetailRepository;
import com.fk.springboot.order.repository.OrderMasterRepository;
import com.fk.springboot.order.services.OrderService;
import com.fk.springboot.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息 调用商品服务
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo_A> productInfoList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            for (ProductInfo_A productInfo : productInfoList){
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    //单价*数量
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    //订单详情
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存 调用服务
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
