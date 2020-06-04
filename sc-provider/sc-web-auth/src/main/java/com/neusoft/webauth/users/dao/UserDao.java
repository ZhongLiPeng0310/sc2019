package com.neusoft.webauth.users.dao;


import com.neusoft.webauth.users.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UsersDao
 * @author zhong
 * @date 2020-03-26
 */
@Mapper
public interface UserDao {
    /**
     * 新增用户
     * @author zhong
     * @date 2020-03-26
     *  @param userInfo 用户信息
     * @return
     */
    int saveUser(UserInfo userInfo);

    /**
     * 查询用户列表
     * @author zhong
     * @date 2020-03-26
     * @param userInfo
     * @return
     */
    List<UserInfo> listUsersByPage(UserInfo userInfo);

    /**
     * 获取用户表中，角色是管理员的编码
     * @author zhong
     * @date 2020-04-10
     * @param listCode
     * @return
     */
    List<String> countUserRole(@Param("listCode") List<String> listCode);
    /**
     * 删除用户信息
     * @param listUserCode
     * @param userId
     * @return
     */
    int deleteUser(@Param("listUserCode") List<String> listUserCode, @Param("userId") String userId);

    /**
     * 校验删除的店长是否已拥有门店
     * @author zhong
     * @date 2020-3-26
     * @param listCode
     * @return
     */
    List<String> countStoreUser(@Param("listCode") List<String> listCode);

    /**
     * 查询当前修改用户的角色
     * @author zhong
     * @date 2020-04-04
     * @param userCode
     * @return
     */
    int getUserRole(@Param("userCode") String userCode);
    /**
     * 修改用户信息
     * @author zhong
     * @date 2020-03-26
     * @param userInfo
     * @return
     */
    int updateUser(UserInfo userInfo);
    /**
     * 获取当前密码
     * @author zhong
     * @date 2020-04-27
     * @return
     */
    UserInfo getUser(@Param("userCode") String userCode);

    /**
     * 查询用户信息
     * @author zhong
     * @date 2020-03-26
     * @param userCode
     * @return
     */
    UserInfo getUserByUserCode(@Param("userCode") String userCode);

    /**
     * 校验用户账号和手机号码是否重复
     * @author zhong
     * @date 2020-04-14
     * @param userInfo
     * @return
     */
    int countUsers(UserInfo userInfo);

    /**
     * 获取顶部导航栏
     * @author zhong
     * @date 2020-04-16
     * @param userInfo
     * @return
     */
    UserInfo getUserUrl(UserInfo userInfo);



}
