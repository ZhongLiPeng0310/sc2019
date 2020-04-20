package com.xzsd.app.client.clientOrder.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientOrderService {
    @Resource
    private ClientOrderDao clientOrderDao;

    /**
     * 新增订单评价
     * @author zhong
     * @date 2002-04-19
     * @param clientOrderInfo
     * @return
     */
    public AppResponse saveOrdersAppraise(ClientOrderInfo clientOrderInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        clientOrderInfo.setUserId(userId);
        clientOrderInfo.setIsDeleted(0);
        clientOrderInfo.setCreateName(userId);
        int saveOrdersAppraise = clientOrderDao.saveOrdersAppraise(clientOrderInfo);
        if (0 == saveOrdersAppraise){
            return AppResponse.bizError("新增失败！");
        }
        return AppResponse.success("新增成功！");
    }
}
