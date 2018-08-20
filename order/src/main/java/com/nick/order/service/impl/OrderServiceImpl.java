package com.nick.order.service.impl;

import com.nick.order.client.ProductClient;
import com.nick.order.dataobject.OrderDetail;
import com.nick.order.dataobject.OrderMaster;
import com.nick.order.dataobject.ProductInfo;
import com.nick.order.dto.CartDTO;
import com.nick.order.dto.OrderDTO;
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
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Autowired
    ProductClient productClient;


    /**
     *
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //  1.查询商品信息(调用商品服务)
        List<String > productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        //  2.计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            for (ProductInfo productInfo: productInfoList){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    //单价*数量
                    orderAmount= productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

        }
        //3.扣除库存（调用商品服务）
        List<CartDTO> cartDTOList =orderDTO.getOrderDetailList().stream()
                                    .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                                    .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
