package com.xzsd.app.client.clientOrder.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.client.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.client.managerOrder.dao.ManagerOrderDao;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientOrderService {
    @Resource
    private ClientOrderDao clientOrderDao;

    /**
     * 客户端修改订单状态
     * @author zhong
     * @date 2020-04-20
     * @param orderCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(String orderCode, int orderState, String userId) {
        //修改订单状态
        int updateOrderState = clientOrderDao.updateOrderState(orderCode,orderState,userId);
        if (0 == updateOrderState){
            return AppResponse.bizError("修改失败！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 客户端新增订单
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveOrder(ClientOrderInfo clientOrderInfo) {
        clientOrderInfo.setOrderCode(StringUtil.getCommonCode(2));
        clientOrderInfo.setIsDeleted(0);
        //新增订单
        int saveOrder = clientOrderDao.saveOrder(clientOrderInfo);
        //新增订单信息到详情表
        int saveOrderDeatail = clientOrderDao.saveOrderDeatail(clientOrderInfo);
        if (0 == saveOrder && 0 == saveOrderDeatail){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 客户端查询订单详情
     * @author zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    public AppResponse getOrdersByCode(String orderCode) {
        ClientOrderInfo clientOrderInfo = clientOrderDao.getOrdersByCode(orderCode);
        return AppResponse.success("查询成功！",clientOrderInfo);
    }
}
