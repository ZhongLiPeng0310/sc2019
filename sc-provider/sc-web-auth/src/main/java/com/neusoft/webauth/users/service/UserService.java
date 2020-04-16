package com.neusoft.webauth.users.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.webauth.users.dao.UserDao;
import com.neusoft.webauth.users.entity.UserInfo;
import com.neusoft.webauth.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 实现类
 * @author zhong
 * @date 2020-03-26
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    /**
     * 新增用户
     * @param userInfo
     * @return
     * @author zhong
     * @date 2020-03-26
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveUser(UserInfo userInfo){
        //检验用户账号和手机号码是否存在
        int countUsers = userDao.countUsers(userInfo);
        if (0 != countUsers){
            return AppResponse.bizError("用户账号或手机号码已存在，请重新输入");
        }
        // 密码加密 默认为123456
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        userInfo.setUserCode(StringUtil.getCommonCode(2));
        userInfo.setIsDeleted(0);
        //新增用户
        int count = userDao.saveUser(userInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询用户列表
     * @param userInfo
     * @author zhong
     * @date 2020-03-26
     * @return
     */
    public AppResponse listUsersByPage(UserInfo userInfo){
        PageHelper.startPage(userInfo.getPageNum(), userInfo.getPageSize());
        List<UserInfo> userInfoList = userDao.listUsersByPage(userInfo);
        //包装Page对象
        PageInfo<UserInfo> pageData = new PageInfo<UserInfo>(userInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 删除用户信息
     * @param userCode
     * @param userId
     * @date 2020-03-26
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userCode,String userId) {
        List<String> listCode = Arrays.asList(userCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = userDao.deleteUser(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @2020-03-26
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        //检验用户账号和手机号码是否存在
        int countUsers = userDao.countUsers(userInfo);
        if (0 != countUsers){
            return AppResponse.bizError("用户账号或手机号码已存在，请重新输入");
        }
        // 修改用户信息
        int count = userDao.updateUser(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 查询用户详情
     * @param userCode
     * @return
     * @Author zhong
     * @date 2020-03-26
     */
    public AppResponse getUserByUserCode(String userCode) {
        UserInfo userInfo = userDao.getUserByUserCode(userCode);
        return AppResponse.success("查询成功！",userInfo);
    }

    /**
     * 获取顶部导航栏
     * @author zhong
     * @date 2020-04-16
     * @param userInfo
     * @return
     */
    public AppResponse getUserUrl(UserInfo userInfo) {
        UserInfo userInfo1 = userDao.getUserUrl(userInfo);
        return AppResponse.success("查询成功！",userInfo1);
    }
}
