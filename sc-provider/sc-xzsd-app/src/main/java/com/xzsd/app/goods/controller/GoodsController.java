package com.xzsd.app.goods.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.goods.entity.GoodsAppraiseInfo;
import com.xzsd.app.goods.entity.GoodsClassInfo;
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
    /**
     * 查询查询商品一级分类接口
     * @author zhong
     * @date 2020-03-26
     * @param goodsClassInfo
     * @return
     */
    @RequestMapping(value = "getFirstClass")
    public AppResponse getFirstClass(GoodsClassInfo goodsClassInfo) {
        try {
            return goodsService.getFirstClass(goodsClassInfo);
        } catch (Exception e) {
            logger.error("商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品二级分类
     * @author zhong
     * @date 2020-03-26
     * @param lastClassCode
     * @return
     */
    @RequestMapping(value = "getSecondClass")
    public AppResponse getSecondClass(String lastClassCode) {
        try {
            return goodsService.getSecondClass(lastClassCode);
        } catch (Exception e) {
            logger.error("商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
