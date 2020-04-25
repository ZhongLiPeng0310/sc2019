package com.xzsd.app.client.driver.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.customer.entity.UserInfo;
import com.xzsd.app.client.driver.service.DriverService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 司机的信息 司机附近的门店
 * @author zhong
 * @date 2020-04-18
 */
@RestController
@RequestMapping("/driver")
public class DriverController {
    @Resource
    private DriverService driverService;
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    /**
     * 查询司机附近门店信息接口
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    @PostMapping("getStoreByDriver")
    private AppResponse getStoreByDriver(UserInfo userInfo){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            AppResponse appResponse = driverService.getStoreByDriver(userInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("查询司机附近门店信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    @PostMapping("getMyself")
    private AppResponse getMyself(UserInfo userInfo){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            AppResponse appResponse = driverService.getMyself(userInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("查询司机信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
        }
}
