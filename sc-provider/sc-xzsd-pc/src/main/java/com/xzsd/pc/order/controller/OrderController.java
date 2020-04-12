package com.xzsd.pc.order.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.entity.OrderInfo;
import com.xzsd.pc.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单管理
 * @author zhong
 * @date 2020-04-06
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;


    /**
     * 查询订单列表 分页
     * @author zhong
     * @date 2020-04-06
     * @param orderInfo
     * @return
     */
    @PostMapping(value = "listOrderByPage")
    public AppResponse listOrderByPage(OrderInfo orderInfo){
        try {
            return orderService.listOrderByPage(orderInfo);
        }catch (Exception e){
            logger.error("查询订单列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单详情
     * @author zhong
     * @date 2020-04-06
     * @param orderCode
     * @return
     */
    @PostMapping("getOrderByOrderCode")
    public AppResponse getOrderByOrderCode(String orderCode){
        try{
            return orderService.getOrderByOrderCode(orderCode);
        }catch (Exception e){
            logger.error("订单详情查询失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改订单状态
     * @author zhong
     * @date 2020-04-12
     * @param orderCode
     * @param orderState
     * @return
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(String orderCode,int orderState){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return orderService.updateOrderState(orderCode,orderState,userId);
        }catch (Exception e){
            logger.error("订单状态修改错误！",e);
            System.out.println(e.toString());
            throw e;
        }
    }


}
