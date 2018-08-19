package com.nick.order.controller;

import com.nick.order.VO.ResultVO;
import com.nick.order.converter.OrderForm2OrderDTOConverter;
import com.nick.order.dto.OrderDTO;
import com.nick.order.enums.ResultEnum;
import com.nick.order.exception.OrderException;
import com.nick.order.form.OrderForm;
import com.nick.order.service.OrderService;
import com.nick.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 1.参数检验
     * 2.查询商品信息(调用商品服务)
     * 3.计算总价
     * 4.扣除库存（调用商品服务）
     * 5.订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确，orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        //oderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if( CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车信息为空，orderForm={}",orderForm);
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result =orderService.create(orderDTO);
        Map<String ,String > map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }
}
