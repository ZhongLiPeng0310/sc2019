package com.xzsd.pc.goods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.service.GoodsService;
import com.xzsd.pc.goodsclass.entity.GoodsClassInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品的增删查改
 * @date 20200324
 * @author zhong
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    /**
     * goods 新增商品
     * @param goodsInfo
     * @return AppResponse
     * @author zhong
     * @date 20200324
     */

    /**
     * Post请求  方法名  saveGoods
     * 新增商品
     * @author zhong
     * @date 2020-03-24
     * @param goodsInfo
     * @return
     */
    @PostMapping("saveGoods")
    public AppResponse saveGoods(GoodsInfo goodsInfo) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setCreateName(userId);
            AppResponse appResponse = goodsService.saveGoods(goodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 查询商品列表(分页)
     * @param goodsInfo
     * @return AppResponse
     * @author zhong
     * @date 2020-03-25
     */
    @RequestMapping(value = "listGoodsByPage")
    public AppResponse listGoodsByPage(GoodsInfo goodsInfo) {
        try {
            return goodsService.listGoodsByPage(goodsInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品
     * @param goodsInfo
     * @author Zhong
     * @date 2020-03-25
     */
    @PostMapping("updateGoods")
    public AppResponse updateGoods(GoodsInfo goodsInfo) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setUpdateName(userId);
            return goodsService.updateGoods(goodsInfo);
        } catch (Exception e) {
            logger.error("修改商品信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 删除商品
     * classname :goodsInfo
     * @author：zhong
     * @date 2020-03-25
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods(String goodsCode) {
        try {
    //     获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return goodsService.deleteGoods(goodsCode,userId);
        } catch (Exception e) {
            logger.error("商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 商品修改上架下架状态
     * @param goodsCode
     * @return
     */
    @PostMapping("updateGoodsState")
    public AppResponse updateGoodsState(String goodsCode,int goodsState,String version) {
        try {
            //     获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return goodsService.updateGoodsState(goodsCode, goodsState , version,userId);
        } catch (Exception e) {
            logger.error("商品修改状态错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查看商品详情
     * @author zhong
     * @date 2020-03-26
     */
    @RequestMapping(value = "getGoodsByGoodsCode")
    public AppResponse getGoodsByGoodsCode(String goodsCode) {
        try {
            return goodsService.getGoodsByGoodsCode(goodsCode);
        } catch (Exception e) {
            logger.error("商品查询错误", e);
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
            logger.error("一级分类查询错误", e);
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
            logger.error("二级分类查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 获取所有门店信息
     * @author zhong
     * @date 2020-04-16
     * @param storeInfo
     * @return
     */
    @RequestMapping(value = "getAllStore")
    public AppResponse getAllStore (StoreInfo storeInfo){
        try{
            return goodsService.getAllStore(storeInfo);
        }catch (Exception e){
            logger.error("查询门店错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
