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

    /**
     * 校验取消的订单状态
     * @date 2020-05-05
     * @author zhong
     * @param listCode
     * @return
     */
    List<Integer> getOrderState(@Param("listCode") List<String> listCode);
    /**
     * 修改订单状态
     * @author zhong
     * @date 2020-04-12
     * @param listCode
     * @return
     */
    int updateOrderState(@Param("listCode") List<String> listCode,@Param("orderState") int orderState,@Param("listVersion")List<String> listVersion, @Param("userId") String userId);

    /**
     * 查询订单的商品编码 购买数量 商品的库存
     * @param listCode
     * @return
     */
    List<OrderInfo> getOrder(@Param("listCode") List<String> listCode);

    /**
     * 修改订单对应商品的库存
     * @param orderInfoList
     * @return
     */
    int updateStock(@Param("orderInfoList") List<OrderInfo> orderInfoList);



}
