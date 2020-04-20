package com.xzsd.app.client.cart.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.cart.entity.CartInfo;
import com.xzsd.app.client.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 购物车
 * @author zhong
 * @date 2020-04-19
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    /**
     * 新增购物车
     * @date 2020-04-19
     * @author zhong
     * @param cartInfo
     * @return
     */
    @PostMapping("saveCart")
    private AppResponse saveCart(CartInfo cartInfo){
        try{
            return cartService.saveCart(cartInfo);
        }catch (Exception e){
            logger.error("新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartInfo
     * @return
     */
    @PostMapping("getCart")
    private AppResponse getCart(CartInfo cartInfo){
        try{
            return cartService.getCart(cartInfo);
        }catch (Exception e){
            logger.error("查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改购物车数量
     * @author zhong
     * @date 2020-04-19
     * @param cartCode
     * @return
     */
    @PostMapping("updateAddSubCart")
    private AppResponse updateAddSubCart(String cartCode,int orderSum){
        try {
            //获取当前登录的用户编码
            String userId = SecurityUtils.getCurrentUserId();
            return cartService.updateAddSubCart(cartCode,orderSum,userId);
        }catch (Exception e){
            logger.error("查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除购物车
     * @author zhong
     * @date 2020-04-19
     * @param cartCode
     * @return
     */
    @PostMapping("deleteCart")
    private AppResponse deleteCart(String cartCode){
        try{
            //获取当前登录的用户编码
            String userId = SecurityUtils.getCurrentUserId();
            return cartService.deleteCart(cartCode,userId);
        }catch (Exception e){
            logger.error("删除失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }


}
