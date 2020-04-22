package com.xzsd.app.client.managerOrder.dao;

import com.xzsd.app.client.managerOrder.entity.ManagerOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  zhong
 * @date 2020-04-20
 */
@Mapper
public interface ManagerOrderDao {
    /**
     * 店长修改订单状态
     * @date 2020-04-21
     * @author zhong
     * @param orderCode
     * @param orderState
     * @param userId
     * @return
     */
    int updateOrderState(@Param("orderCode") String orderCode,@Param("orderState") int orderState,@Param("userId") String userId);

    /**
     * 店长查询订单详情
     * @author zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    ManagerOrderInfo getStoreOrdersByCode(@Param("orderCode") String orderCode);

    /**
     * 店长查询订单列表
     * @author zhong
     * @date 2020-04-21
     * @param managerOrderInfo
     * @return
     */
    List<ManagerOrderInfo> getStoreOrdersList(ManagerOrderInfo managerOrderInfo);
}
