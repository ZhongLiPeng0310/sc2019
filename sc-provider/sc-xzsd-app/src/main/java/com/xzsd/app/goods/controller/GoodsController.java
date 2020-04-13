package com.xzsd.app.goods.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.goods.entity.GoodsAppraiseInfo;
import com.xzsd.app.goods.entity.GoodsInfo;
import com.xzsd.app.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * app端
 * 商品展示
 * 商品评价
 * 一二级分类
 * @author zhong
 * @date 2020-04-13
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    /**
     * app查询商品详情
     * @author zhong
     * @date 2020-04-13
     * @param goodsCode
     * @return
     */
    @PostMapping("getGoods")
    public AppResponse getGoods(String goodsCode){
        try{
            return goodsService.getGoods(goodsCode);
        }catch (Exception e){
            logger.error("查询商品详情错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * app查询商品评价列表
     * @author zhong
     * @date 2020-04-13
     * @param goodsAppraiseInfo
     * @return
     */
    @PostMapping("getGoodsAppraise")
    public AppResponse getGoodsAppraise(GoodsAppraiseInfo goodsAppraiseInfo){
        try{
            return goodsService.getGoodsAppraise(goodsAppraiseInfo);
        }catch (Exception e){
            logger.error("查询商品评价错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
