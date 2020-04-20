package com.xzsd.app.client.clientOrder.controller;

import com.neusoft.core.restful.AppResponse;
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
@RequestMapping("/goods")
public class ClientOrderController {
    @Resource
    private ClientOrderService clientOrderService;
    private static final Logger logger = LoggerFactory.getLogger(ClientOrderController.class);

    /**
     * 新增订单评价
     * @author zhong
     * @date 2020-04-19
     * @param clientOrderInfo
     * @return
     */
    @PostMapping("saveOrdersAppraise")
    private AppResponse saveOrdersAppraise(ClientOrderInfo clientOrderInfo){
        try {
            return clientOrderService.saveOrdersAppraise(clientOrderInfo);
        }catch (Exception e){
            logger.error("新增订单评价失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
