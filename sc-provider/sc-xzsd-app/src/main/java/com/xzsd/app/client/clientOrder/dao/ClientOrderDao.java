package com.xzsd.app.client.clientOrder.dao;

import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientOrderDao {
    /**
     * 新增订单评价
     * @author zhong
     * @date 2020-04-19
     * @param clientOrderInfo
     * @return
     */
    int saveOrdersAppraise(ClientOrderInfo clientOrderInfo);
}
