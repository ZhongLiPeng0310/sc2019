package com.xzsd.pc.store.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.dictionary.entity.DictionaryInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import com.xzsd.pc.store.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 * 门店信息管理
 * 增删改查
 * @author zhong
 * @date 2020-04-06
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    /**
     * 新增门店
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    @PostMapping("saveStore")
    public AppResponse saveStore(StoreInfo storeInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            storeInfo.setCreateName(userId);
            AppResponse appResponse = storeService.saveStore(storeInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("门店信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店列表  分页
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    @PostMapping(value = "listStoreByPage")
    public AppResponse listStoreByPage(StoreInfo storeInfo){
        try{
            return storeService.listStoreByPage(storeInfo);
        }catch (Exception e){
            logger.error("查询门店列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     *删除门店
     * @author zhong
     * @date 2020-04-06
     * @param storeCode
     * @return
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore(String storeCode) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return storeService.deleteStore(storeCode, userId);
        } catch (Exception e) {
            logger.error("门点删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改门店信息
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    @PostMapping("updateStore")
    public AppResponse updateStore(StoreInfo storeInfo){
        try{
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            storeInfo.setUpdateName(userId);
            return storeService.updateStore(storeInfo);
        }catch (Exception e){
            logger.error("修改门店信息错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店详情
     * @param storeCode
     * @return
     */
    @PostMapping(value = "getStoreByStoreCode")
    public AppResponse getStoreByStoreCode(String storeCode){
        try{
            return storeService.getStoreByStoreCode(storeCode);
        }catch (Exception e){
            logger.error("门店查询错误");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询省份列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    @PostMapping("listProvince")
    public AppResponse listProvince(DictionaryInfo dictionaryInfo){
        try {
            return storeService.listProvince(dictionaryInfo);
        }catch (Exception e){
            logger.error("查询省份列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询城市列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    @PostMapping("listCity")
    public AppResponse listCity(DictionaryInfo dictionaryInfo){
        try {
            return storeService.listCity(dictionaryInfo);
        }catch (Exception e){
            logger.error("查询城市列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询区列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    @PostMapping("listArea")
    public AppResponse listArea(DictionaryInfo dictionaryInfo){
        try {
            return storeService.listArea(dictionaryInfo);
        }catch (Exception e){
            logger.error("查询区列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
