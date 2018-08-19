package com.nick.order.service;

import com.nick.order.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    OrderDTO create(OrderDTO orderDto);
}
