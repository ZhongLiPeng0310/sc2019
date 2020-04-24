package com.xzsd.app.client.clientOrder.dao;

import com.xzsd.app.client.clientOrder.entity.CartOrderInfo;
import com.xzsd.app.client.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.client.clientOrder.entity.GoodsAppraiseInfo;
import com.xzsd.app.client.clientOrder.entity.ImageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

@Mapper
public interface ClientOrderDao {


    /**
     * 修改订单状态
     * @date 2020-04-21
     * @author zhong
     * @param orderState
     * @param orderState
     * @param userId
     * @return
     */
    int updateOrderState(@Param("orderCode") String orderCode,@Param("orderState") int orderState,@Param("userId") String userId);

    /**
     * 客户端新增订单
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    int saveOrder(ClientOrderInfo clientOrderInfo);

    /**
     * 客户端新增订单到订单详情
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    int saveOrderDeatail(ClientOrderInfo clientOrderInfo);
    /**
     * 获取下单商品的库存
     * @param clientOrderInfo
     * @return
     */
    int nowStock(ClientOrderInfo clientOrderInfo);
    /**
     * 更新下单商品的库存数量
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    int updateStock(ClientOrderInfo clientOrderInfo);
    /**
     * 获取下单商品的价格
     * @author zhong
     * @date 2020-04-22
     * @param clientOrderInfo
     * @return
     */
    float getPrice(ClientOrderInfo clientOrderInfo);
    /**
     * 增加下单商品的销售量
     * @author zhong
     * @date 2020-04-22
     * @param clientOrderInfo
     * @return
     */
    int updateSumSale(ClientOrderInfo clientOrderInfo);
    /**
     * 获取当前商品的销售量
     * @author zhong
     * @date 2020-04-22
     * @param clientOrderInfo
     * @return
     */
    int getSumSale(ClientOrderInfo clientOrderInfo);
    /**
     * 客户端查询订单详情
     * @author zhong
     * @date 2020-04-21
     * @param orderCode
     * @return
     */
    ClientOrderInfo getOrdersByCode(@Param("orderCode") String orderCode);
    /**
     * 新增订单评价
     * @author zhong
     * @date 2020-04-23
     * @param goodsAppraiseInfoList
     * @return
     */
    int saveOrdersAppraise(@Param("goodsAppraiseInfoList") List<GoodsAppraiseInfo> goodsAppraiseInfoList);
    /**
     * 新增评价图片
     * @author zhong
     * @date 2020-04-23
     * @param goodsAppraiseInfo
     * @return
     */
    List<ImageInfo> saveAppraiseImage(GoodsAppraiseInfo goodsAppraiseInfo);


    /**
     * 客户查询订单列表
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    List<ClientOrderInfo> getOrdersList(ClientOrderInfo clientOrderInfo);

    /**
     * 新增订单到订单表
     * @param orderCode
     * @param userId
     * @param orderMoney
     * @param storeCode
     * @return
     */
    int saveCartOrder(@Param("orderCode") String orderCode,@Param("userId") String userId,@Param("orderMoney") float orderMoney,@Param("storeCode") String storeCode,@Param("sumGoods") int sumGoods);



    /**
     * 新增订单到订单详细表
     * @author zhong
     * @date 2020-04-23
     * @param cartOrderInfoList
     * @return
     */
    int saveCartOrderDetail(@Param("cartOrderInfoList") List<CartOrderInfo> cartOrderInfoList);

    /**
     * 删除新增订单后购物车的商品
     * @author zhong
     * @date 2020-04-24
     * @param listCart
     * @return
     */
    int updateCartGoods(@Param("listCart")List<String> listCart,@Param("userId") String userId);
}
