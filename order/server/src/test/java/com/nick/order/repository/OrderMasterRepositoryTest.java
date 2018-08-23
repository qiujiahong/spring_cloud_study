package com.nick.order.repository;

import com.nick.order.OrderApplicationTests;
import com.nick.order.dataobject.OrderMaster;
import com.nick.order.enums.OrderStatusEnum;
import com.nick.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("中文名字");
        orderMaster.setBuyerPhone("18000000000");
        orderMaster.setBuyerAddress("深圳南山");
        orderMaster.setBuyerOpenid("openidwx");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}