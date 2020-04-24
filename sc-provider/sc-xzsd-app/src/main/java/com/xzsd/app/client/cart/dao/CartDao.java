package com.xzsd.app.client.cart.dao;

import com.xzsd.app.client.cart.entity.CartInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDao {
    /**
     * 新增购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    int saveCart(CartInfo cartInfo);
    /**
     * 查找当前商品在购物车的数量
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    int findSum(CartInfo cartInfo);
    /**
     * 校验新增的商品是否已在购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    int checkSave(CartInfo cartInfo);
    /**
     * 查询新增商品的库存
     * @author zhong
     * @date 2020-04-24
     * @param cartInfo
     * @return
     */
    int countStock(CartInfo cartInfo);
    /**
     * 更新购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    int updateCart(CartInfo cartInfo);
    /**
     * 查询购物车  分页
     * @date 2020-04-19
     * @author zhong
     * @param cartInfo
     * @return
     */
    List<CartInfo> getCart(CartInfo cartInfo);

    /**
     * 修改购物车商品数量
     * @author zhong
     * @date 2020-04-19
     * @param cartCode
     * @param orderSum
     * @param userId
     * @return
     */
    int updateAddSubCart(@Param("cartCode") String cartCode,@Param("orderSum") int orderSum,@Param("userId") String userId);

    /**
     * 删除购物车
     * @author zhong
     * @date 2020-019
     * @param listCode
     * @param userId
     * @return
     */
    int deleteCart(@Param("listCode")List<String> listCode,@Param("userId") String userId);


}
