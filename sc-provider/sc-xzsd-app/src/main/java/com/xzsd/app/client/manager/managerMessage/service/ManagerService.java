package com.xzsd.app.client.manager.managerMessage.service;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.client.manager.managerMessage.dao.ManagerDao;
import com.xzsd.app.client.manager.managerMessage.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerService {
    @Resource
    private ManagerDao managerDao;

    /**
     * 查询店长信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    public AppResponse getStoreByCode(UserInfo userInfo) {
        UserInfo managerMessage = managerDao.getStoreByCode(userInfo);
        return AppResponse.success("查询成功！",managerMessage);
    }

    /**
     * 查询门店附近司机信息
     * @author zhong
     * @date 2020-04-18
     * @param userInfo
     * @return
     */
    public AppResponse getStoreDriverByCode(UserInfo userInfo) {
        List<UserInfo> driverlist = managerDao.getStoreDriverByCode(userInfo);
        return AppResponse.success("查询成功！",driverlist);
    }
}
