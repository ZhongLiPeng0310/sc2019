package com.xzsd.app.client.clientOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.client.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.client.clientOrder.entity.*;
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
     * @param clientOrderInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(ClientOrderInfo clientOrderInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        clientOrderInfo.setUserId(userId);
        //修改订单状态
        int updateOrderState = clientOrderDao.updateOrderState(clientOrderInfo);
        if (clientOrderInfo.getOrderState() == 9){
            //查询订单的商品编码 购买数量 商品的库存
            List<ClientOrderInfo> clientOrderInfoList = clientOrderDao.getOrder(clientOrderInfo.getOrderCode());
            //修改商品库存
            int updateStock = clientOrderDao.updateStock(clientOrderInfoList);
            if (0 == updateStock){
                return AppResponse.bizError("数据无变化，请重试！");
            }
        }else if (clientOrderInfo.getOrderState() == 5){
            //查询订单的商品编码 购买数量 商品的库存
            List<ClientOrderInfo> clientOrderInfoList = clientOrderDao.getOrder(clientOrderInfo.getOrderCode());
            //修改商品销售量
            int updateStock = clientOrderDao.updateSumSale(clientOrderInfoList);
            if (0 == updateStock){
                return AppResponse.bizError("数据无变化，请重试！");
            }
        }
        if (0 == updateOrderState){
            return AppResponse.bizError("数据无变化，请重试！");
        }
        return AppResponse.success("修改成功！");
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
    public AppResponse saveOrdersAppraise(GoodsAppraiseInfo goodsAppraiseInfo,String orderCode,String goodsCode ,String appraiseDetail,String appraiseLevel,String userId,String avgLevel) {
        List<String> listDatail = Arrays.asList(appraiseDetail.split("@end"));
        List<String> listGoods = Arrays.asList(goodsCode.split(","));
        List<String> listLevel = Arrays.asList(appraiseLevel.split(","));
        List<GoodsAppraiseInfo> goodsAppraiseInfoList = new ArrayList<GoodsAppraiseInfo>();

        int number = listGoods.size();
        //根据页面传回来的数量插入
        for (int i = 0 ;i < number; i++){
            GoodsAppraiseInfo appraiseInfo = new GoodsAppraiseInfo();
            appraiseInfo.setAppraiseDetail(listDatail.get(i));
            appraiseInfo.setAppraiseLevel(listLevel.get(i));
//            appraiseInfo.setAvgLevel(level.get(i));
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
        //查询评价的商品的星级
        List<ClientOrderInfo> levelList = clientOrderDao.countLevel(listGoods);
        //更新商品的评价等级
        int updateGoodsLevel = clientOrderDao.updateGoodsLevel(levelList);
        if (0 == saveOrdersAppraise){
            return AppResponse.bizError("评价失败！");
        }
            return AppResponse.success("评价成功！");
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
        //查询当前下单商品的库存
        List<Integer> countGoodsStock = clientOrderDao.countGoodsStock(listGoods);
        for (int i = 0;i < listGoods.size(); i++){
            if (0 == countGoodsStock.get(i) || Integer.parseInt(listSum.get(i)) > countGoodsStock.get(i)){
                return AppResponse.bizError("新增失败！商品库存不足");
            }
        }
//        //查询当前下单商品的销售量
//        List<Integer> countSumSale = clientOrderDao.countSumSale(listGoods);
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
            orderInfo.setOrderSum(Integer.parseInt(listSum.get(i)));
//            orderInfo.setSumSale(countSumSale.get(i));
            orderInfo.setStock(countGoodsStock.get(i));
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
        }else{
            //新增订单后删除购物车中的商品
            int updateCartGoods = clientOrderDao.updateCartGoods(listCart,userId);
            //修改下单商品的库存
            int updateGoodsStock = clientOrderDao.updateGoodsStock(cartOrderInfoList);
            return AppResponse.success("新增成功！");
        }
    }

    /**
     * 新增评价  json格式
     * @author zhong
     * @date 2020-04-25
    * @param appraiseOrderInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveAppraise(AppraiseOrderInfo appraiseOrderInfo) {
        String userCode = SecurityUtils.getCurrentUserId();
        for (int i = 0; i < appraiseOrderInfo.getAppraiseInfoList().size(); i++){
            //设置评价信息
            appraiseOrderInfo.getAppraiseInfoList().get(i).setAppraiseCode(StringUtil.getCommonCode(2));
            appraiseOrderInfo.getAppraiseInfoList().get(i).setCreateName(userCode);
            appraiseOrderInfo.getAppraiseInfoList().get(i).setIsDeleted(0);
        }
        //新增评价
        int saveAppraise = clientOrderDao.saveAppraise(appraiseOrderInfo);
        //查询评价的商品的星级
        List<String> level = clientOrderDao.countGoodsLevel(appraiseOrderInfo);
        for (int i = 0; i < appraiseOrderInfo.getAppraiseInfoList().size(); i++){
            appraiseOrderInfo.getAppraiseInfoList().get(i).setGoodsCode(appraiseOrderInfo.getAppraiseInfoList().get(i).getGoodsCode());
            appraiseOrderInfo.getAppraiseInfoList().get(i).setAvgLevel(level.get(i));
        }
        //更新商品的评价等级
        int updateGoodsLevel = clientOrderDao.updateLevel(appraiseOrderInfo);
        if (0 == saveAppraise ||0 == updateGoodsLevel){
            return AppResponse.bizError("评价失败");
        }
        return AppResponse.success("评价成功！");
    }
}
