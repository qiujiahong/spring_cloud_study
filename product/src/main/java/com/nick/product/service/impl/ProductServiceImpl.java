package com.nick.product.service.impl;

import com.nick.product.dataobject.ProductInfo;
import com.nick.product.enums.ProductStatusEnum;
import com.nick.product.repository.ProductInfoRepository;
import com.nick.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
