package com.hwua.pojo;

import java.util.Date;
import lombok.Data;

/**
 * (Product)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Data
public class Product {
    
    private String id;
    /**
    * 产品编号
    */
    private String productNum;
    /**
    * 产品名称
    */
    private String productName;
    /**
    * 出发城市
    */
    private String cityName;
    /**
    * 出发时间
    */
    private String departureTime;
    /**
    * 产品价格
    */
    private Double productPrice;
    /**
    * 产品描述
    */
    private String productDesc;
    /**
    * 产品状态(0关闭,1开启)
    */
    private Integer productStatus;

}