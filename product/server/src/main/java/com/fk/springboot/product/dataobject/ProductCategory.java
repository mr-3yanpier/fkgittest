package com.fk.springboot.product.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//@Data
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
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

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
