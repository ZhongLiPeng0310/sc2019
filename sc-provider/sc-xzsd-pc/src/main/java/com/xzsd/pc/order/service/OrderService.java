package com.xzsd.pc.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.OrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private CustomerDao customerDao;
    /**
     * 查询订单列表  分页
     * @author zhong
     * @date 2020-04-06
     * @param orderInfo
     * @return
     */
    public AppResponse listOrderByPage(OrderInfo orderInfo) {
        //查询当前登录人的的id
        String userId = SecurityUtils.getCurrentUserId();
        orderInfo.setUserId(userId);
        //查询当前登录人的角色
        String role = customerDao.getUserRole(userId);
        orderInfo.setRole(role);
        PageHelper.startPage(orderInfo.getPageNum(), orderInfo.getPageSize());
        List<OrderInfo> orderInfoList = orderDao.listOrderByPage(orderInfo);
        //包装Page对象
        PageInfo<OrderInfo> pageData = new PageInfo<OrderInfo>(orderInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 查询订单详情
     * @author zhong
     * @date 2020-04-06
     * @param orderCode
     * @return
     */
    public AppResponse getOrderByOrderCode(String orderCode) {
        OrderInfo orderInfo = orderDao.getOrderByOrderCode(orderCode);
        return AppResponse.success("查询成功！",orderInfo);
    }

    /**
     * 修改订单状态
     * @author zhong
     * @date 2020-04-12
     * @param orderCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(String orderCode,int orderState,String userId) {
        //分割订单编码，逗号隔开
        List<String> listCode = Arrays.asList(orderCode.split(","));
        //修改订单状态
        int count = orderDao.updateOrderState(listCode,orderState,userId);
        if (orderState == 9){
            //查询订单的商品编码 购买数量 商品的库存
            List<OrderInfo> orderInfoList = orderDao.getOrder(listCode);
            //修改商品库存
            int updateStock = orderDao.updateStock(orderInfoList);
            if (0 == updateStock){
                return AppResponse.bizError("数据无变化，请重试！");
            }
        }
        if (0 == count){
            return AppResponse.bizError("数据无变化，请重试！");
        }
        return AppResponse.success("修改成功！");
    }
}
