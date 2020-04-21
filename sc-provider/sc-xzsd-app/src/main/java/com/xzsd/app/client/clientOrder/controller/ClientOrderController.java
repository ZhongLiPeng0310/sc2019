package com.xzsd.app.client.clientOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.client.clientOrder.entity.GoodsAppraiseInfo;
import com.xzsd.app.client.clientOrder.entity.ImageInfo;
import com.xzsd.app.client.clientOrder.service.ClientOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 新增订单
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    @PostMapping("saveOrder")
    private AppResponse saveOrder(ClientOrderInfo clientOrderInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            clientOrderInfo.setCreateName(userId);
            return clientOrderService.saveOrder(clientOrderInfo);
        }catch (Exception e){
            logger.error("新增成功！");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 客户端获取订单详情
     * @autho zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    @PostMapping("getOrdersByCode")
    private AppResponse getOrdersByCode(String orderCode){
        try{
            return clientOrderService.getOrdersByCode(orderCode);
        }catch (Exception e){
            logger.error("查询详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 新增订单评价
     * @author zhong
     * @date 2020-04-21
     * @param goodsAppraiseInfo
     * @return
     */
    @PostMapping("saveOrdersAppraise")
    private AppResponse saveOrdersAppraise(GoodsAppraiseInfo goodsAppraiseInfo, List<ImageInfo> imageInfoList){
        try {
            return clientOrderService.saveOrdersAppraise(goodsAppraiseInfo,imageInfoList);
        }catch (Exception e){
            logger.error("新增订单评价失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 客户查询订单列表接口
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    @PostMapping("getOrdersList")
    private AppResponse getOrdersList(ClientOrderInfo clientOrderInfo){
         try{
             return clientOrderService.getOrdersList(clientOrderInfo);
         }catch (Exception e){
             logger.error("查询订单列表失败",e);
             System.out.println(e.toString());
             throw e;
         }
    }
}
