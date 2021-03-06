package com.neusoft.webauth.users.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.neusoft.util.StringUtil;
import com.neusoft.webauth.users.dao.UserDao;
import com.neusoft.webauth.users.entity.UserInfo;
import com.neusoft.webauth.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        // 密码加密
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        //随机生成编码
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
     * @author zhong
     * @date 2020-03-26
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userCode,String userId) {
        List<String> listCode = Arrays.asList(userCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //获取用户表中，角色是管理员的编码
        List<String> userCodeList = userDao.countUserRole(listCode);
        //获取用户表中，角色是店长，且拥有门店
        List<String> userStoreList = userDao.countStoreUser(listCode);
        //排除是管理员\店长，且拥有门店的编码
        ArrayList listUserCode = new ArrayList(listCode);
        listUserCode.removeAll(userCodeList);
        listUserCode.removeAll(userStoreList);
        //删除的用户存在管理员，删除失败
        if (0 == listUserCode.size()){
            return AppResponse.bizError("删除的用户是管理员或者拥有门店的店长，无此权限!");
        }
        // 删除用户
        int deleteUser = userDao.deleteUser(listUserCode,userId);
        if (userCodeList.size() != 0 && listUserCode.size() != 0 && 0 != deleteUser){
            return AppResponse.success("删除成功！，用户编码为" + userCodeList +"的用户是管理员，无法删除！");
        }
        if (userStoreList.size() != 0 && listUserCode.size() != 0 && 0 != deleteUser){
            return AppResponse.success("删除成功！，用户编码为" + userCodeList +"的用户拥有门店，无法删除！");
        }
        if(0 == deleteUser) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @author zhong
     * @2020-03-26
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
            //修改密码
            //当前在页面上的密码
            String oldPassword = userInfo.getUserPassword();
            //获取用户的原密码已加密 需要校验密码是否改动
            UserInfo user = userDao.getUser(userInfo.getUserCode());
            //校验原密码和显示密码是否相同
            if(oldPassword.equals(user.getUserPassword())) {
                userInfo.setUserPassword(oldPassword);
            }else{
                String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
                userInfo.setUserPassword(password);
            }
            //检验用户账号和手机号码是否存在
            int countUsers = userDao.countUsers(userInfo);
            if (0 != countUsers){
                return AppResponse.bizError("用户账号或手机号码已存在，请重新输入");
            }
            //获取修改的用户的角色
            int getRole = userDao.getUserRole(userInfo.getUserCode());
            if (getRole == 3 && userInfo.getRole() == 1){
                return AppResponse.bizError("修改失败，不允许跨级修改角色");
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
        UserInfo userInfoMessage = userDao.getUserUrl(userInfo);
        return AppResponse.success("查询成功！",userInfoMessage);
    }
}
