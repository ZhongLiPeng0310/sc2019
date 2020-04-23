package com.xzsd.app.client.clientOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.client.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.client.clientOrder.entity.CartOrderInfo;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.client.clientOrder.entity.GoodsAppraiseInfo;
import com.xzsd.app.client.clientOrder.entity.ImageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ClientOrderService {
    @Resource
    private ClientOrderDao clientOrderDao;

    /**
     * 客户端修改订单状态
     * @author zhong
     * @date 2020-04-21
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
     * 客户端在商品页面新增订单
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveOrder(ClientOrderInfo clientOrderInfo) {
        clientOrderInfo.setOrderCode(StringUtil.getCommonCode(2));
        String userId = SecurityUtils.getCurrentUserId();
        clientOrderInfo.setCreateName(userId);
        clientOrderInfo.setIsDeleted(0);
        //新增订单
        int saveOrder = clientOrderDao.saveOrder(clientOrderInfo);
        //新增订单信息到详情表
        int saveOrderDeatail = clientOrderDao.saveOrderDeatail(clientOrderInfo);
        if (0 == saveOrder || 0 == saveOrderDeatail){
            return AppResponse.bizError("新增失败，请重试！");
        }else {
            //获取当前商品的库存数量
            int nowStock = clientOrderDao.nowStock(clientOrderInfo);
            clientOrderInfo.setStock(nowStock);
            int countGoods = clientOrderInfo.getOrderSum();
            clientOrderInfo.setSumOrder(countGoods);
            //修改该商品的库存数量
            int updateStock = clientOrderDao.updateStock(clientOrderInfo);
            //获取下单商品当前的销售量
            int sumSale = clientOrderDao.getSumSale(clientOrderInfo);
            clientOrderInfo.setSumSale(sumSale);
            //增加商品的销售量
            int updateSumSale = clientOrderDao.updateSumSale(clientOrderInfo);
            return AppResponse.success("新增成功！");
        }

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

    /**
     * 客户端新增订单评价
     * @author zhong
     * @date 2020-04-23
     * @param appraiseDetail
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveOrdersAppraise(GoodsAppraiseInfo goodsAppraiseInfo,String orderCode,String goodsCode ,String appraiseDetail,String appraiseLevel,String userId) {
        List<String> listDatail = Arrays.asList(appraiseDetail.split(","));
        List<String> listGoods = Arrays.asList(goodsCode.split(","));
        List<String> listLevel = Arrays.asList(appraiseLevel.split(","));
        List<GoodsAppraiseInfo> goodsAppraiseInfoList = new ArrayList<GoodsAppraiseInfo>();
        int number = listGoods.size();
        //根据页面传回来的数量插入
        for (int i = 0 ;i < number; i++){
            GoodsAppraiseInfo appraiseInfo = new GoodsAppraiseInfo();
            appraiseInfo.setAppraiseDetail(listDatail.get(i));
            appraiseInfo.setAppraiseLevel(listLevel.get(i));
            appraiseInfo.setGoodsCode(listGoods.get(i));
            appraiseInfo.setOrderCode(orderCode);
            appraiseInfo.setUserId(userId);
            appraiseInfo.setIsDeleted(0);
            appraiseInfo.setCreateTime(new Date());
            appraiseInfo.setAppraiseCode(StringUtil.getCommonCode(2));
            appraiseInfo.setVersion(0);
            goodsAppraiseInfoList.add(appraiseInfo);
        }
        //新增评价
        int saveOrdersAppraise = clientOrderDao.saveOrdersAppraise(goodsAppraiseInfoList);
//        //新增评价图片
//        int addImage = clientOrderDao.addImages(imageInfoList);
        if (0 == saveOrdersAppraise){
            return AppResponse.bizError("新增失败！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 客户查询订单列表
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    public AppResponse getOrdersList(ClientOrderInfo clientOrderInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        clientOrderInfo.setUserId(userId);
        clientOrderInfo.setIsDeleted(0);
        PageHelper.startPage(clientOrderInfo.getPageNum(), clientOrderInfo.getPageSize());
        List<ClientOrderInfo> goodsInfoList = clientOrderDao.getOrdersList(clientOrderInfo);
        //包装Page对象
        PageInfo<ClientOrderInfo> pageData= new PageInfo<ClientOrderInfo>(goodsInfoList);
        return AppResponse.success("查询列表成功！", pageData);
    }

    /**
     * 在购物车新增订单
     * @author zhong
     * @date 2020-04-23
     * @param goodsCode
     * @param orderSum
     * @param cartCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveCartOrder(String goodsCode,String orderSum, String cartCode,float orderMoney,String storeCode) {
        List<String> listGoods = Arrays.asList(goodsCode.split(","));
        List<String> listSum = Arrays.asList(orderSum.split(","));
        List<String> listCart = Arrays.asList(cartCode.split(","));
        List<CartOrderInfo> cartOrderInfoList = new ArrayList<CartOrderInfo>();
        String userId = SecurityUtils.getCurrentUserId();

        //生成订单编码
        String orderCode = StringUtil.getCommonCode(2);
        //订单购买的不同商品总数
        int sumGoods = listCart.size();
        for (int i = 0; i < listGoods.size(); i++){
            CartOrderInfo orderInfo = new CartOrderInfo();
            orderInfo.setDetailCode(StringUtil.getCommonCode(1));
            orderInfo.setOrderCode(orderCode);
            orderInfo.setUserId(userId);
            orderInfo.setOrderMoney(orderMoney);
            orderInfo.setCartCode(listCart.get(i));
            orderInfo.setGoodsCode(listGoods.get(i));
            orderInfo.setOrderSum(listSum.get(i));
            orderInfo.setIsDeleted(0);
            orderInfo.setCreateTime(new Date());
            orderInfo.setVersion(0);
            cartOrderInfoList.add(orderInfo);
        }
        //新增订单到订单表
        int saveCartOrder = clientOrderDao.saveCartOrder(orderCode,userId,orderMoney,storeCode,sumGoods);
        //新增订单到订单详情表
        int saveCartOrderDetail = clientOrderDao.saveCartOrderDetail(cartOrderInfoList);
        if (0 == saveCartOrder || 0 == saveCartOrderDetail){
            return AppResponse.bizError("新增失败！");
        }
        return AppResponse.success("新增成功！");
    }
}
