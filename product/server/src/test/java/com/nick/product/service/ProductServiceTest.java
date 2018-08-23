package com.nick.product.service;

import com.nick.product.common.ProductInfoOutput;
import com.nick.product.dto.CartDTO;
import com.nick.product.ProductApplicationTests;
import com.nick.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class ProductServiceTest extends ProductApplicationTests {


    @Autowired
    ProductService productService ;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void findList() {
        List<ProductInfoOutput> list =  productService.findList(
                Arrays.asList("157875196366160022","164103465734242707"));
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void decreaseStock() {
        CartDTO cartDTO = new CartDTO("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}