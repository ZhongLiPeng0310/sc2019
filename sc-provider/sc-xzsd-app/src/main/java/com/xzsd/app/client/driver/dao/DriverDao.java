package com.xzsd.app.client.driver.dao;

import com.xzsd.app.client.manager.managerMessage.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DriverDao {
    /**
     * 查询司机附近门店信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    UserInfo getStoreByDriver(UserInfo userInfo);

    /**
     * 查询司机信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    UserInfo getMyself(UserInfo userInfo);


}
