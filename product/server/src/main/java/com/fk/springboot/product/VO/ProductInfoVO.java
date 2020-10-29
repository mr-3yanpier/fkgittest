package com.fk.springboot.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

//@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescprition;

    @JsonProperty("icon")
    private String productIcon;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductDescprition() {
        return productDescprition;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductDescprition(String productDescprition) {
        this.productDescprition = productDescprition;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }
}
