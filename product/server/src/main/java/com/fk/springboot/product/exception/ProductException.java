package com.fk.springboot.product.exception;

import com.fk.springboot.product.enums.ResultEnum;

public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
