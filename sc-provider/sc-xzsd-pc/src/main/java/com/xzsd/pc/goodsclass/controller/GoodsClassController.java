package com.xzsd.pc.goodsclass.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goodsclass.entity.classFirstList;
import com.xzsd.pc.goodsclass.entity.GoodsClassInfo;
import com.xzsd.pc.goodsclass.service.GoodsClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品分类增删查改
 * @author zhong
 * @date 2020-03-27
 */
@RestController
@RequestMapping("/goodsclass")
public class GoodsClassController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsClassController.class);

    @Resource
    private GoodsClassService goodsClassService;

    /**
     * 新增商品分类
     *
     * @author zhong
     * @date 2020-03-27
     */

    @PostMapping("saveGoodsClass")
    public AppResponse saveGoodsClass(GoodsClassInfo goodsClassInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            goodsClassInfo.setCreateName(userId);
            AppResponse appResponse = goodsClassService.saveGoodsClass(goodsClassInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品分类新增失败", e);
            throw e;
        }
    }

    /**
     * 查询商品分类列表
     * @param classFirstList
     * @author zhong
     * @date 2020-03-27
     * @return
     */
    @RequestMapping(value = "listGoodsClass")
    public AppResponse listGoodsClass(classFirstList classFirstList){
        try{
            return goodsClassService.listGoodsClass(classFirstList);
        }catch (Exception e){
            logger.error("查询商品分类列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品分类
     * @author zhong
     * @date 2020-03-27
     * @param goodsClassInfo
     * @return
     */
    @PostMapping("deleteGoodsClass")
    public AppResponse deleteGoodsClass(GoodsClassInfo goodsClassInfo){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            goodsClassInfo.setUpdateName(userId);
            return goodsClassService.deleteGoodsClass(goodsClassInfo);
        }catch (Exception e){
            logger.error("商品分类删除失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品分类
     * @param goodsClassInfo
     * @author zhong
     * @date 2020-03-27
     * @return
     */
    @PostMapping("updateGoodsClass")
    public AppResponse updateGoodsClass(GoodsClassInfo goodsClassInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            goodsClassInfo.setUpdateName(userId);
            return goodsClassService.updateGoodsClass(goodsClassInfo);
        }catch (Exception e){
            logger.error("商品分类修改失败", e);
            throw e;
        }
    }


    /**
     * 查询分类详情
     * @author zhong
     * @date 2020-03-27
     * @return AppResponse
     * @param classCode
     * @return
     */
    @RequestMapping(value = "getClassByClassCode")
    public AppResponse getClassByClassCode(String classCode){
        try{
            return goodsClassService.getClassByClassCode(classCode);
        }catch (Exception e){
            logger.error("分类查询错误");
            System.out.println(e.toString());
            throw e;
        }
    }


}
