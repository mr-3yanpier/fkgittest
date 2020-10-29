package com.fk.springboot.product.service;

import com.fk.springboot.product.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
