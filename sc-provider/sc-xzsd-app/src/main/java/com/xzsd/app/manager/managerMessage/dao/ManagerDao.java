package com.xzsd.app.manager.managerMessage.dao;

import com.xzsd.app.manager.managerMessage.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerDao {
    /**
     * 查询店长信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    UserInfo getStoreByCode(UserInfo userInfo);

    /**
     * 查询附近司机的信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    List<UserInfo> getStoreDriverByCode(UserInfo userInfo);
}
