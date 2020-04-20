package com.xzsd.app.client.clientOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.client.clientOrder.service.ClientOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单评价 订单列表
 * @author zhong
 * @date 2020-04-19
 */
@RestController
@RequestMapping("/order")
public class ClientOrderController {
    @Resource
    private ClientOrderService clientOrderService;
    private static final Logger logger = LoggerFactory.getLogger(ClientOrderController.class);


    /**
     * 客户端修改订单状态
     * @author zhong
     * @date 2020-04-20
     * @param orderCode
     * @return
     */
    @PostMapping("updateOrderState")
    private AppResponse updateOrderState(String orderCode,int orderState){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            return clientOrderService.updateOrderState(orderCode,orderState,userId);
        }catch (Exception e){
            logger.error("修改订单状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
