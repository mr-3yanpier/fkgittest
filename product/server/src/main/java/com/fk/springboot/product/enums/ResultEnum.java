package com.fk.springboot.product.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    RPODUCT_NOT_EXIT(1, "商品不存在"),
    RPODUCT_STOCK_ERROR(2, "库存有误"),
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
