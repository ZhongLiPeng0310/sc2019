package com.xzsd.pc.pictureShow.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.pictureShow.entity.PictureShowInfo;
import com.xzsd.pc.pictureShow.service.PictureShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 轮播图管理  增删改查
 * @author zhong
 * @date 2020-04-05
 */
@RestController
@RequestMapping("/pictureShow")
public class PictureShowController {
    private static final Logger logger = LoggerFactory.getLogger(PictureShowController.class);

    @Resource
    private PictureShowService pictureShowService;

    /**
     * 新增轮播图
     * @author zhong
     * @date 2020-04-04
     * @param pictureShowInfo
     * @return
     */
    @PostMapping("savePictureShow")
    public AppResponse savePictureShow(PictureShowInfo pictureShowInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            pictureShowInfo.setCreateName(userId);
            AppResponse appResponse = pictureShowService.savePictureShow(pictureShowInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("轮播图新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询轮播图列表
     * @author zhong
     * @date 2020-04-05
     * @param pictureShowInfo
     * @return
     */
    @PostMapping(value = "listPictureShowByPage")
    public AppResponse listPictureShowByPage(PictureShowInfo pictureShowInfo){
        try{
            return pictureShowService.listPictureShowByPage(pictureShowInfo);
        }catch (Exception e){
            logger.error("查询轮播图列表异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除轮播图
     * @param showCode
     * @return
     */
    @PostMapping("deletePictureShow")
    public AppResponse deletePictureShow(String showCode){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return pictureShowService.deletePictureShow(showCode,userId);
        }catch (Exception e){
            logger.error("轮播图删除错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改轮播图启用禁用状态接口
     * @author zhong
     * @date 2020-04-05
     * @param showCode
     * @param showState
     * @return
     */
    @PostMapping("updatePictureShowState")
    public AppResponse updatePictureShowState(String showCode,int showState) {
        try {
            //     获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return pictureShowService.updatePictureShowState(showCode, showState ,userId);
        } catch (Exception e) {
            logger.error("商品修改状态错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 连接商品列表
     * @param goodsInfo
     * @return
     */
    @RequestMapping(value = "listGoodsByPage")
    public AppResponse listGoodsByPage(GoodsInfo goodsInfo) {
        try {
            return pictureShowService.listGoodsByPage(goodsInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
