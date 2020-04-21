package com.xzsd.app.client.clientOrder.dao;

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
     * @date 2020-04-20
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
     * @date 2020-04-21
     * @param goodsAppraiseInfo
     * @return
     */
    int saveOrdersAppraise(GoodsAppraiseInfo goodsAppraiseInfo);

    /**
     * 新增评价图片
     * @author zhong
     * @date 2020-04-21
     * @param goodsAppraiseInfo
     * @return
     */
    List<ImageInfo> saveAppraiseImage(GoodsAppraiseInfo goodsAppraiseInfo);

    /**
     * 新增评价图片
     * @author zhong
     * @date 2020-04-21
     * @param imageInfoList
     * @return
     */
    int addImages(@Param("imageInfoList") List<ImageInfo> imageInfoList);

    /**
     * 客户查询订单列表
     * @author zhong
     * @date 2020-04-21
     * @param clientOrderInfo
     * @return
     */
    List<ClientOrderInfo> getOrdersList(ClientOrderInfo clientOrderInfo);


}
