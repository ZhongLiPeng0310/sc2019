package com.xzsd.app.client.manager.managerMessage.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.manager.managerMessage.entity.UserInfo;
import com.xzsd.app.client.manager.managerMessage.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 店长端 查询店长信息
 */
@RestController
@RequestMapping("/storer")
public class ManagerController {
    @Resource
    private ManagerService managerService;
    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    /**
     * 查询店长信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    @PostMapping("getStoreByCode")
    private AppResponse getStoreByCode(UserInfo userInfo){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            return managerService.getStoreByCode(userInfo);
        }catch (Exception e){
            logger.error("查询店长门店信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询附近司机信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    @PostMapping("getStoreDriverByCode")
    private AppResponse getStoreDriverByCode(UserInfo userInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            return managerService.getStoreDriverByCode(userInfo);
        }catch (Exception e){
            logger.error("查询附近司机信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
