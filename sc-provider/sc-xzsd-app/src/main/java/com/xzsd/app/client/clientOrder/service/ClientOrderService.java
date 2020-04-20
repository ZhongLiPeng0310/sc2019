package com.xzsd.app.client.clientOrder.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.client.managerOrder.dao.ManagerOrderDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientOrderService {
    @Resource
    private ManagerOrderDao managerOrderDao;

    /**
     * 客户端修改订单状态
     * @author zhong
     * @date 2020-04-20
     * @param orderCode
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
