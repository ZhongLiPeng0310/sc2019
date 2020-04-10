package com.xzsd.pc.order.dao;


import com.xzsd.pc.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    /**
     * 查询订单列表  分页
     * @author zhong
     * @date 2020-04-06
     * @param orderInfo
     * @return
     */
    List<OrderInfo> listOrderByPage(OrderInfo orderInfo);

    /**
     * 查询订单详情
     * @author zhong
     * @date 2020-04-06
     * @param orderCode
     * @return
     */
    OrderInfo getOrderByOrderCode(@Param("orderCode") String orderCode);
}
