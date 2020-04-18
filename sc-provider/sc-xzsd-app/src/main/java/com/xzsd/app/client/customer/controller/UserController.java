package com.xzsd.app.client.customer.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.client.customer.service.UserService;
import com.xzsd.app.client.customer.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 增删查改
 * @author zhong
 * @date 2020-03-26
 */
@RestController
@RequestMapping("/client")
public class UserController {
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    /**
     * 新增用户
     * @param userInfo
     * @return
     * @author zhong
     * @date 2020-03-26
     */
    @PostMapping("registUSer")
    public AppResponse saveUser(UserInfo userInfo){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setCreateName(userId);
            AppResponse appResponse = userService.saveUser(userInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("用户新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改密码
     * @author zhong
     * @date 2020-04-16
     * @param userInfo
     * @return
     */
    @PostMapping("updateUserPassword")
    public AppResponse updateUserPassword(UserInfo userInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUpdateName(userId);
            userInfo.setUserCode(userId);
            return userService.updateUserPassword(userInfo);
        } catch (Exception e) {
            logger.error("修改异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询客户信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    @PostMapping("getMyself")
    private AppResponse getMyself(UserInfo userInfo){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            return userService.getMyself(userInfo);
        }catch (Exception e){
            logger.error("查询客户信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改客户邀请码
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    @PostMapping("updateInviteCode")
    private AppResponse updateInviteCode(UserInfo userInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            return userService.updateInviteCode(userInfo);
        }catch (Exception e){
            logger.error("修改邀请码失败！",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
