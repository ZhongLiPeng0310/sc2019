package com.xzsd.pc.hotGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.sun.jersey.core.impl.provider.entity.XMLRootObjectProvider;
import com.xzsd.pc.dictionary.entity.DictionaryInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import com.xzsd.pc.hotGoods.service.HotGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *热门商品管理
 * 增删查改
 * @author zhong
 * @date 2020-04-04
 */
@RestController
@RequestMapping("/hotGoods")
public class HotGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(HotGoodsController.class);

    @Resource
    private HotGoodsService hotGoodsService;

    /**
     * 新增热门商品
     * @param hotGoodsInfo
     * @return
     */
    @PostMapping("/saveHotGoods")
    public AppResponse saveHotGoods (HotGoodsInfo hotGoodsInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setCreateName(userId);
            AppResponse appResponse = hotGoodsService.saveHotGoods(hotGoodsInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("热门商品新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品列表  分页
     * @author zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    @PostMapping(value = "listHotGoodsByPage")
    public AppResponse listHotGoodsByPage(HotGoodsInfo hotGoodsInfo){
        try{
            return hotGoodsService.listHotGoodsByPage(hotGoodsInfo);
        }catch (Exception e){
            logger.error("查询热门商品列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除热门商品
     * @author zhong
     * @date 2020-04-04
     * @param hotCode
     * @return
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods(String hotCode)  {
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return hotGoodsService.deleteHotGoods(hotCode,userId);
        }catch (Exception e){
            logger.error("热门商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门商品信息
     * @author zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    @PostMapping("updateHotGoods")
    public AppResponse updateHotGoods(HotGoodsInfo hotGoodsInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setUpdateName(userId);
            return hotGoodsService.updateHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("修改热门商品错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 设置热门商品展示数量
     * @author zhong
     * @date 2020-04-10
     * @param dictionaryInfo
     * @return
     */
    @PostMapping("saveShowNo")
    public AppResponse saveShowNo(DictionaryInfo dictionaryInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            dictionaryInfo.setCreateName(userId);
            return hotGoodsService.saveShowNo(dictionaryInfo);
        }catch (Exception e){
            logger.error("设置热门商品展示数量错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品展示数量
     * @author
     * @date 2020-04-10
     * @param dictionaryInfo
     * @return
     */
    @PostMapping(value = "getShowNo")
    public AppResponse getShowNoByDicCode(DictionaryInfo dictionaryInfo){
        try{
            return hotGoodsService.getShowNo(dictionaryInfo);
        }catch (Exception e){
            logger.error("热门商品展示数量查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品详情
     * @author zhong
     * @date 2020-04-10
     * @param hotCode
     * @return
     */
    @RequestMapping(value = "getHotGoodsByHotCode")
    public AppResponse getHotGoodsByHotCode(String hotCode) {
        try {
            return hotGoodsService.getHotGoodsByHotCode(hotCode);
        } catch (Exception e) {
            logger.error("热门商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
