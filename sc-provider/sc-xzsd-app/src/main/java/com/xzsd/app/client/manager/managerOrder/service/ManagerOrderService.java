package com.xzsd.app.client.manager.managerOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.manager.managerOrder.dao.ManagerOrderDao;
import com.xzsd.app.client.manager.managerOrder.entity.ManagerOrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerOrderService {
    @Resource
    private ManagerOrderDao managerOrderDao;

    /**
     * 店长修改订单状态
     * @author zhong
     * @date 2020-04-21
     * @param orderCode
     * @param orderState
     * @param userId
     * @return
     */
    public AppResponse updateOrderState(String orderCode, int orderState,String version, String userId) {
        //修改订单状态
        int updateOrderState = managerOrderDao.updateOrderState(orderCode,orderState,version,userId);
        if (0 == updateOrderState){
            return AppResponse.bizError("修改失败！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 店长查询订单详情
     * @author zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    public AppResponse getStoreOrdersByCode(String orderCode) {
        ManagerOrderInfo managerOrderInfo = managerOrderDao.getStoreOrdersByCode(orderCode);
        return AppResponse.success("查询成功！",managerOrderInfo);
    }

    /**
     * 店长查询订单列表
     * @author zhong
     * @date 2020-04-21
     * @param managerOrderInfo
     * @return
     */
    public AppResponse getStoreOrdersList(ManagerOrderInfo managerOrderInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        managerOrderInfo.setUserId(userId);
        managerOrderInfo.setIsDeleted(0);
        PageHelper.startPage(managerOrderInfo.getPageNum(), managerOrderInfo.getPageSize());
        List<ManagerOrderInfo> managerOrderInfoList = managerOrderDao.getStoreOrdersList(managerOrderInfo);
        //包装Page对象
        PageInfo<ManagerOrderInfo> pageData= new PageInfo<ManagerOrderInfo>(managerOrderInfoList);
        return AppResponse.success("查询列表成功！", pageData);
    }
}
