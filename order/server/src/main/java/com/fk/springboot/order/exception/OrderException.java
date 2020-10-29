package com.fk.springboot.order.exception;

import com.fk.springboot.order.enums.ResultEnum;

public class OrderException extends  RuntimeException{

    private Integer code;

    public OrderException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
