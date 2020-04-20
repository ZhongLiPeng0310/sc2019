package com.xzsd.app.client.managerOrder.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.client.managerOrder.dao.ManagerOrderDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerOrderService {
    @Resource
    private ManagerOrderDao managerOrderDao;

    /**
     * 店长修改订单状态
     * @author zhong
     * @date 2020-04-20
     * @param orderCode
     * @param orderState
     * @param userId
     * @return
     */
    public AppResponse updateOrderState(String orderCode, int orderState, String userId) {
        //修改订单状态
        int updateOrderState = managerOrderDao.updateOrderState(orderCode,orderState,userId);
        if (0 == updateOrderState){
            return AppResponse.bizError("修改失败！");
        }
        return AppResponse.success("修改成功！");
    }

}
