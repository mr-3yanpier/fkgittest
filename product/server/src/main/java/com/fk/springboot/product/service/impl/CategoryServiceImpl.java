package com.fk.springboot.product.service.impl;

import com.fk.springboot.product.dataobject.ProductCategory;
import com.fk.springboot.product.repository.ProductCategoryRepository;
import com.fk.springboot.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
