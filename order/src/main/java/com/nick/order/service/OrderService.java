package com.nick.order.service;

import com.nick.order.dto.OrderDto;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    OrderDto create(OrderDto orderDto);
}
