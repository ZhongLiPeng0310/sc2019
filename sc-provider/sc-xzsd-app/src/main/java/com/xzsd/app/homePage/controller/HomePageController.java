package com.xzsd.app.homePage.controller;

import com.neusoft.core.restful.AppResponse;
import com.sun.corba.se.spi.activation.ServerIdsHolder;
import com.xzsd.app.homePage.entity.HotGoodsInfo;
import com.xzsd.app.homePage.entity.PictureShowInfo;
import com.xzsd.app.homePage.service.HomePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页轮播图 热门商品
 * @author zhong
 * @date 2020-04-12
 */
@RestController
@RequestMapping("/homePage")
public class HomePageController {

    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);
    @Resource
    private HomePageService homePageService;

    /**
     * 查询首页轮播图
     * @author zhong
     * @date 2020-04-12
     * @param pictureShowInfo
     * @return
     */
    @PostMapping("getPictureShow")
    public AppResponse getPictureShow(PictureShowInfo pictureShowInfo){
        try{
            return homePageService.getPictureShow(pictureShowInfo);
        }catch (Exception e){
            logger.error("查询轮播图失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询首页热门商品
     * @author zhong
     * @date 2020-04-12
     * @param hotGoodsInfo
     * @return
     */
    @PostMapping("getHotGoods")
    public AppResponse getHotGoods(HotGoodsInfo hotGoodsInfo){
        try {
            return homePageService.getHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("查询首页热门商品失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
