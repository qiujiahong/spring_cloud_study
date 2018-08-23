package com.nick.order.controller;

import com.nick.order.dto.CartDTO;
import com.nick.order.dataobject.ProductInfo;
import com.nick.product.client.ProductClient;
import com.nick.product.common.DecreaseStockInput;
import com.nick.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;


    @GetMapping("/getProductList")
    public String  getProductList(){
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}",productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("164103465734242707",2)));
        return "ok";
    }

}

