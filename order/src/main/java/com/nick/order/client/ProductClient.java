package com.nick.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/msg") //访问product下面msg这个接口
    String productMsg();
}
