package com.xzsd.app.client.customer.dao;


import com.xzsd.app.client.customer.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * UsersDao
 * @author zhong
 * @date 2020-03-26
 */
@Mapper
public interface UserDao {
    /**
     * 统计用户账号数量
     * @author zhong
     * @date 2020-03-26
     * @param userInfo 用户信息
     * @return
     */
    int countUserAcct(UserInfo userInfo);
    /**
     * 校验用户手机号码是否存在
     * @author zhong
     * @date 2020-03-26
     * @param userInfo
     * @return
     */
    int countUserPhone(UserInfo userInfo);
    /**
     * 校验用户邮箱是否存在
     * @author zhong
     * @date 2020-04-10
     * @param userInfo
     * @return
     */
    int countEmail(UserInfo userInfo);

    /**
     * 校验用户账号和手机号码是否重复
     * @author zhong
     * @date 2020-04-14
     * @param userInfo
     * @return
     */
    int countUsers(UserInfo userInfo);

    /**
     * 新增客户信息用户客户表
     * @author zhong
     * @date 2020-03-26
     *  @param userInfo 用户信息
     * @return
     */
    int userRegister(UserInfo userInfo);

    /**
     * 新增用户信息到客户表
     * @author zhong
     * @date 2020-03-26
     * @param userInfo
     * @return
     */
    int saveUserToCus(UserInfo userInfo);

    /**
     * 获取用户信息
     * @author zhong
     * @date 2020-04-16
     * @param userCode
     * @return
     */
    UserInfo getUserById(String userCode);

    /**
     * 修改密码
     * @author zhong
     * @date 2020-04-16
     * @param userInfo
     * @return
     */
    int updateUserPassword(UserInfo userInfo);

    /**
     * 获取用户信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    UserInfo getMysef(UserInfo userInfo);

    /**
     * 修改用户邀请码
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    int updateInviteCode(UserInfo userInfo);
}
