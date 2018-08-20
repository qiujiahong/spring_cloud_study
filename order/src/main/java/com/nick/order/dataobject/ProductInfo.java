package com.nick.order.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class ProductInfo {

    @Id
    private String productId;
    private String productName;    //'商品名称'
    private BigDecimal productPrice;//'单价'
    private Integer productStock;//'库存'
    private String productDescription;//'描述'
    private String productIcon;            //'小图'
    private Integer productStatus;     //'商品状态,0正常1下架'
    private Integer categoryType;      //'类目编号'
    private Date createTime;           //'创建时间'
    private Date updateTime;           //'修改时间'

}
