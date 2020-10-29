package com.fk.springboot.product.utils;

import com.fk.springboot.product.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object ojb){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(ojb);

        return resultVO;

    }
}
