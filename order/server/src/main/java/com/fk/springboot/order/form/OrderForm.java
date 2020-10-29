package com.fk.springboot.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填！")
    private String name;

    @NotEmpty(message = "手机必填！")
    private String phone;

    @NotEmpty(message = "地址必填！")
    private String address;

    @NotEmpty(message = "openID必填！")
    private String openid;

    @NotEmpty(message = "购物车不为能空！")
    private String items;
}
