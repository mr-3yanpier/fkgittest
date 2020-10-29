package com.fk.springboot.product.service.impl;

import com.fk.springboot.product.common.ProductInfoOutput;
import com.fk.springboot.product.dto.CartDTO;
import com.fk.springboot.product.dataobject.ProductInfo;
import com.fk.springboot.product.enums.ResultEnum;
import com.fk.springboot.product.exception.ProductException;
import com.fk.springboot.product.repository.ProductInfoRepository;
import com.fk.springboot.product.service.ProductService;
import com.fk.springboot.product.utils.JsonUtil;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(0);
    }

    @Override
    public List<ProductInfo> findProductListById(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            //商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.RPODUCT_NOT_EXIT);
            }

            //库存是否够
            ProductInfo productInfo = productInfoOptional.get();
            Integer tmpStock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(tmpStock < 0 ){
                throw new ProductException(ResultEnum.RPODUCT_NOT_EXIT);
            }

            productInfo.setProductStock(tmpStock);
            productInfoRepository.save(productInfo);

            //发送MQ消息
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(productInfo, productInfoOutput);
            amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutput));
        }
    }


}
