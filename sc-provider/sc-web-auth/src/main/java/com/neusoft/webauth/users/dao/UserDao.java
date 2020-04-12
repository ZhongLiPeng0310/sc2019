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
     * 删除用户信息
     * @param listCode
     * @param userId
     * @return
     */
    int deleteUser(@Param("listCode") List<String> listCode, @Param("userId") String userId);

    /**
     * 修改用户信息
     * @author zhong
     * @date 2020-03-26
     * @param userInfo
     * @return
     */
    int updateUser(UserInfo userInfo);

    /**
     * 查询用户信息
     * @author zhong
     * @date 2020-03-26
     * @param userCode
     * @return
     */
    UserInfo getUserByUserCode(@Param("userCode") String userCode);


}
