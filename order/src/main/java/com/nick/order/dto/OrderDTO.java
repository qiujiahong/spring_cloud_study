package com.nick.order.dto;

import com.nick.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;  //买家名字
    private String buyerPhone; //买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid; //买家微信openid
    private BigDecimal orderAmount; //订单总金额
    private Integer orderStatus;    //订单状态, 默认为新下单,
    private Integer payStatus;     //支付状态, 默认未支付,默认为0
    private List<OrderDetail> orderDetailList;
}
