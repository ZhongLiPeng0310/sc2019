package com.xzsd.app.client.managerOrder.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author  zhong
 * @date 2020-04-20
 */
@Mapper
public interface ManagerOrderDao {
    /**
     * 修改店长订单状态
     * @date 2020-04-20
     * @author zhong
     * @param orderCode
     * @param orderState
     * @param userId
     * @return
     */
    int updateOrderState(@Param("orderCode") String orderCode,@Param("orderState") int orderState,@Param("userId") String userId);
}
