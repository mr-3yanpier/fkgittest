package com.fk.springboot.product.controller;

import com.fk.springboot.product.dto.CartDTO;
import com.fk.springboot.product.VO.ProductInfoVO;
import com.fk.springboot.product.VO.ProductVO;
import com.fk.springboot.product.VO.ResultVO;
import com.fk.springboot.product.dataobject.ProductCategory;
import com.fk.springboot.product.dataobject.ProductInfo;
import com.fk.springboot.product.service.CategoryService;
import com.fk.springboot.product.service.ProductService;
import com.fk.springboot.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductContrallor {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询所有在架的商品
     * 2.获取类目type
     * 3.查询类目
     * 4.构造数据
     */

    @GetMapping("list")
    public ResultVO<ProductVO> list() {

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表（给订单服务使用）
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findProductListById(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }
}
