package com.nick.order.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;    //商品名称
    private BigDecimal productPrice;   //当前价格,单位分
    private Integer productQuantity;   //数量
    private String productIcon;        //小图
    private Date createTime;
    private Date updateTime;

}
