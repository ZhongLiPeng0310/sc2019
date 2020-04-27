package com.neusoft.webauth.users.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.neusoft.webauth.users.entity.UserInfo;
import com.neusoft.webauth.users.service.UserService;
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
@RequestMapping("/user")
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
    @PostMapping("saveUser")
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
     * 查询用户列表（分页）
     * @param userInfo
     * @author zhong
     * @date 2020-03-26
     * @return
     */
    @PostMapping(value = "listUsersByPage")
    public AppResponse listUsersByPage(UserInfo userInfo){
        try{
            return userService.listUsersByPage(userInfo);
        }catch (Exception e){
            logger.error("查询用户列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除用户信息
     * @param userCode
     * 2020-03-26
     * @return
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(String userCode) {
        try {
            //获取用户id
            String userId = AuthUtils.getCurrentUserId();
            return userService.deleteUser(userCode,userId);
        } catch (Exception e) {
            logger.error("用户删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改用户信息
     * @param userInfo
     * zhong
     * @date 2020-03-26
     * @return
     */
    @PostMapping("updateUser")
    public AppResponse updateUser(UserInfo userInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUpdateName(userId);
            return userService.updateUser(userInfo);
        } catch (Exception e) {
            logger.error("修改用户信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询用户详情，根据用户编码查询
     * @param userCode
     * @return
     * @author zhong
     * @date 2020-03-26
     */
    @PostMapping("getUserByUserCode")
    public AppResponse getUserByUserCode(String userCode){
        try{
            return userService.getUserByUserCode(userCode);
        }catch (Exception e){
            logger.error("用户查询错误");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 获取顶部导航栏
     * @author zhong
     * @date 2020-04-16
     * @param userInfo
     * @return
     */
    @PostMapping(value = "getUserUrl")
    public AppResponse getUserUrl(UserInfo userInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            return userService.getUserUrl(userInfo);
        }catch (Exception e){
            logger.error("导航栏查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
