package com.nick.product.utils;

import com.nick.product.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(obj);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return  resultVO;
    }
}
