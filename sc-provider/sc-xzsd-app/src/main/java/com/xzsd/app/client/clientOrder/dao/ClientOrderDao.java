package com.xzsd.app.client.clientOrder.dao;

import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

@Mapper
public interface ClientOrderDao {


    /**
     * 修改订单状态
     * @date 2020-04-20
     * @author zhong
     * @param orderState
     * @param orderState
     * @param userId
     * @return
     */
    int updateOrderState(@Param("orderCode") String orderCode,@Param("orderState") int orderState,@Param("userId") String userId);
}
