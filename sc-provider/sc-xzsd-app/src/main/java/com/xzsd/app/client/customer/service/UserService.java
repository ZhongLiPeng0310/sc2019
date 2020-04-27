package com.xzsd.app.client.customer.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.client.customer.dao.UserDao;
import com.xzsd.app.client.customer.entity.UserInfo;
import com.xzsd.app.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
     * 注册用户
     * @param userInfo
     * @return
     * @author zhong
     * @date 2020-03-26
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse userRegister(UserInfo userInfo){
        //检验用户账号和手机号码是否存在
        int countUsers = userDao.countUsers(userInfo);
        if (0 != countUsers){
            return AppResponse.bizError("用户账号或手机号码已存在，请重新输入");
        }
        if(null != userInfo.getInviteCode() && !"".equals(userInfo.getInviteCode())) {
            //校验店铺邀请码是否存在
            String inviteCode =  userInfo.getInviteCode();
            int countInvite = userDao.countInvite(inviteCode);
            if (0 == countInvite) {
                return AppResponse.bizError("邀请码不存在，请重新输入");
            }
        }
        // 密码加密 默认为123
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        userInfo.setUserCode(StringUtil.getCommonCode(2));
        userInfo.setIsDeleted(0);
        //新增客户信息到用户表
        int count = userDao.userRegister(userInfo);
        //新增客户信息到客户表
        int countTnCustomer = userDao.saveUserToCus(userInfo);
        if (0 == count && 0 ==countTnCustomer){
            return AppResponse.bizError("注册失败，请重试！");
        }
        return AppResponse.success("注册成功！");
    }

    /**
     * 修改密码
     * @author zhong
     * @date 2020-04-16
     * @param userInfo
     * @return
     */
    public AppResponse updateUserPassword(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        // 需要校验原密码是否正确
        if(null != userInfo.getUserPassword() && !"".equals(userInfo.getUserPassword())) {
                //获取用户id
                String userId = SecurityUtils.getCurrentUserId();
                userInfo.setUpdateName(userId);
                userInfo.setUserCode(userId);
                String oldPassword = userInfo.getUserPassword();
                //原密码已加密
                UserInfo user = userDao.getUser(userInfo.getUserCode());
                if(!PasswordUtils.Password(oldPassword,user.getUserPassword())) {
                    return AppResponse.bizError("原密码不匹配，请重新输入！");
                }
        }
        // 修改密码
        userInfo.setUserNewPassword(PasswordUtils.generatePassword(userInfo.getUserNewPassword()));
        int count = userDao.updateUserPassword(userInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改密码失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 获取用户信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    public AppResponse getMyself(UserInfo userInfo) {
        UserInfo clientMessage = userDao.getMysef(userInfo);
        return AppResponse.success("查询成功！",clientMessage);
    }

    /**
     * 修改用户邀请码
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    public AppResponse updateInviteCode(UserInfo userInfo) {
        //校验店铺邀请码是否存在
        String inviteCode =  userInfo.getInviteCode();
        int countInvite = userDao.countInvite(inviteCode);
        if (0 == countInvite) {
            return AppResponse.bizError("邀请码不存在，请重新输入");
        }
        AppResponse appResponse = AppResponse.success("修改成功！");
        int updateCode = userDao.updateInviteCode(userInfo);
        if (0 == updateCode){
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }
}
