package com.fk.springboot.order.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

//@Table(name = "product_info")
//@Data
@Entity
public class ProductInfo_A {
    @Id
    private String productId ;
    private String productName ;
    private BigDecimal productPrice ;
    private Integer productStock ;
    private String productDescription ;
    private String productIcon ;
    private Integer productStatus ;
    private Integer categoryType ;
    private Date createTime ;
    private Date updateTime ;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
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

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
