package com.nick.order.client;

import com.nick.order.dataobject.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/msg") //访问product下面msg这个接口
    String productMsg();
    @PostMapping("/product/listForOrder")
    public List<ProductInfo> listForOrder(List<String> productIdList);
}
