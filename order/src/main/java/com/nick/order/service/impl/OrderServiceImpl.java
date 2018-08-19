package com.nick.order.service.impl;

import com.nick.order.dataobject.OrderMaster;
import com.nick.order.dto.OrderDto;
import com.nick.order.enums.OrderStatusEnum;
import com.nick.order.enums.PayStatusEnum;
import com.nick.order.repository.OrderDetailRepository;
import com.nick.order.repository.OrderMasterRepository;
import com.nick.order.service.OrderService;
import com.nick.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    /**
     *
     */
    @Override
    public OrderDto create(OrderDto orderDto) {

        // TODO 1.查询商品信息(调用商品服务)
        // todo 2.计算总价
        // todo 3.扣除库存（调用商品服务）

        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDto;
    }
}
