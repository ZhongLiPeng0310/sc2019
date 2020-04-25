package com.xzsd.app.client.driver.service;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.client.customer.entity.UserInfo;
import com.xzsd.app.client.driver.dao.DriverDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DriverService {
    @Resource
    private DriverDao driverDao;

    /**
     * 查询司机附近门店信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    public AppResponse getStoreByDriver(UserInfo userInfo) {
        List<UserInfo> driverMessage = driverDao.getStoreByDriver(userInfo);
        return AppResponse.success("查询成功！",driverMessage);
    }

    /**
     * 查询司机信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    public AppResponse getMyself(UserInfo userInfo) {
        UserInfo DriverInfo = driverDao.getMyself(userInfo);
        return AppResponse.success("查询成功！",DriverInfo);
    }
}
