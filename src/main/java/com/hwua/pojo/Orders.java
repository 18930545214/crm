package com.hwua.pojo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

/**
 * (Orders)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Data
public class Orders {
    
    private String id;
    /**
    * 订单编号
    */
    private String orderNum;
    /**
    * 下单时间
    */
    private Date orderTime;
    /**
    * 出行人数
    */
    private Integer peopleCount;
    /**
    * 订单描述
    */
    private String orderDesc;
    /**
    * 支付方式(0支付宝 1微信 2其它)
    */
    private Integer payType;
    /**
    * 订单状态(0未支付 1已支付)
    */
    private Integer orderStatus;
    /**
    * 产品id外键
    */
    private String productId;
    /**
    * 会员联系人id外键
    */
    private String memberId;
    /**
     * 产品,一对一关系
     */
    private List<Product> products;
    /**
     * 会员联系人
     */
    private Member member;

}