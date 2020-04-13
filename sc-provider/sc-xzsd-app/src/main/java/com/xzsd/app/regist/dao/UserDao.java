package com.xzsd.app.regist.dao;


import com.xzsd.app.regist.entity.UserInfo;
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

}