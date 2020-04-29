package com.xzsd.app.client.cart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.client.cart.dao.CartDao;
import com.xzsd.app.client.cart.entity.CartInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
    @Resource
    private CartDao cartDao;

    /**
     * 新增购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    public AppResponse saveCart(CartInfo cartInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        cartInfo.setUserId(userId);
        cartInfo.setCreateName(userId);
        cartInfo.setIsDeleted(0);
        cartInfo.setCartCode(StringUtil.getCommonCode(2));
        //检验新增的商品库存
        int countStock = cartDao.countStock(cartInfo);
        if (countStock == 0 || countStock < cartInfo.getOrderSum()){
            return AppResponse.bizError("新增失败，商品库存不足");
        }
        //检验新加的商品是否已经存在购物车
        int checkSave = cartDao.checkSave(cartInfo);
        if (0 != checkSave){
            //查找当前的数量
            int nowSum = cartDao.findSum(cartInfo);
            cartInfo.setNewOrderSum(nowSum);
            //更新当前数量
            int updateCart = cartDao.updateCart(cartInfo);
            if (0 != updateCart){
                return AppResponse.success("加入成功！");
            }
        }
            //加入购物车
            int saveCart = cartDao.saveCart(cartInfo);
            if (0 == saveCart){
                return AppResponse.bizError("加入失败！");
            }
        return AppResponse.success("加入成功！");
    }

    /**
     * 查询购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    public AppResponse getCart(CartInfo cartInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        cartInfo.setUserId(userId);
        PageHelper.startPage(cartInfo.getPageNum(),cartInfo.getPageSize());
        List<CartInfo> cartInfoList = cartDao.getCart(cartInfo);
        //包装Page对象
        PageInfo<CartInfo> pageData = new PageInfo<>(cartInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * 修改购物车商品加减数量
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    public AppResponse updateAddSubCart(CartInfo cartInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        //检验在购物车中新增的商品库存
        int countStock = cartDao.countCartStock(cartInfo);
        if (countStock == 0 || countStock < cartInfo.getOrderSum()){
            return AppResponse.bizError("商品库存不足");
        }
        //修改购物车商品数量
        int updateAddSubCart = cartDao.updateAddSubCart(cartInfo);
        if (0 == updateAddSubCart){
            return AppResponse.bizError("修改失败");
        }
        return appResponse;
    }

    /**
     * 删除购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartCode
     * @param userId
     * @return
     */
    public AppResponse deleteCart(String cartCode, String userId) {
        AppResponse appResponse = AppResponse.success("删除成功");
        List<String> listCode = Arrays.asList(cartCode.split(","));
        //删除购物车
        int deleteCart = cartDao.deleteCart(listCode,userId);
        if (0 == deleteCart){
            return AppResponse.bizError("删除失败");
        }
        return appResponse;
    }
}
