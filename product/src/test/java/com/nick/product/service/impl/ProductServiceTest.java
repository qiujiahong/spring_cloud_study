package com.nick.product.service.impl;

import com.nick.product.ProductApplicationTests;
import com.nick.product.dataobject.ProductInfo;
import com.nick.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceTest extends ProductApplicationTests {


    @Autowired
    ProductService productService ;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size()>0);
    }
}