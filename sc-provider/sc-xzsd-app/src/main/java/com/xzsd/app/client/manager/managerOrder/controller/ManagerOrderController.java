package com.xzsd.app.client.manager.managerOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.manager.managerOrder.entity.ManagerOrderInfo;
import com.xzsd.app.client.manager.managerOrder.service.ManagerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 店长订单
 * @author zhong
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/storeOrder")
public class ManagerOrderController {
    @Resource
    private ManagerOrderService managerOrderService;

    private static final Logger logger = LoggerFactory.getLogger(ManagerOrderController.class);

    /**
     * 店长修改订单状态
     * @author zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    @PostMapping("updateOrderState")
    private AppResponse updateOrderState(String orderCode, int orderState){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            return managerOrderService.updateOrderState(orderCode,orderState,userId);
        }catch (Exception e){
            logger.error("修改订单状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 店长获取订单详情
     * @autho zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    @PostMapping("getStoreOrdersByCode")
    private AppResponse getStoreOrdersByCode(String orderCode){
        try{
            return managerOrderService.getStoreOrdersByCode(orderCode);
        }catch (Exception e){
            logger.error("查询详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 店长查询订单列表接口
     * @author zhong
     * @date 2020-04-21
     * @param managerOrderInfo
     * @return
     */
    @PostMapping("getStoreOrdersList")
    private AppResponse getStoreOrdersList(ManagerOrderInfo managerOrderInfo){
        try{
            return managerOrderService.getStoreOrdersList(managerOrderInfo);
        }catch (Exception e){
            logger.error("查询订单列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
