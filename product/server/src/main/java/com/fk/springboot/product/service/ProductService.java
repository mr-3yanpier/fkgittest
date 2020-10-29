package com.fk.springboot.product.service;

import com.fk.springboot.product.dto.CartDTO;
import com.fk.springboot.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {

    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findProductListById(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
