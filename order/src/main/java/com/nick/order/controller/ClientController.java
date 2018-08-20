package com.nick.order.controller;

import com.nick.order.client.ProductClient;
import com.nick.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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


    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种方式，写死地址
//        RestTemplate restTemplate = new RestTemplate();
//        String response =  restTemplate.getForObject("http://localhost:8080/msg",String.class);


        //2.第二种,通过应用名获url，然后再使用第一种方式处理
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%d/msg",serviceInstance.getHost(),serviceInstance.getPort());
//        String response =  restTemplate.getForObject(url,String.class);

        //3.利用@LoadBalanced注解，可在restTemplate里使用应用名字
//        String response =  restTemplate.getForObject("http://PRODUCT/msg",String.class);

        String response = productClient.productMsg();
        log.info("resoponse={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public String  getProductList(){
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}",productInfoList);
        return "ok";
    }

}

