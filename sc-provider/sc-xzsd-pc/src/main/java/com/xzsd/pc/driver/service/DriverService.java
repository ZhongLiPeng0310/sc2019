package com.xzsd.pc.driver.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.DriverInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 实现类
 */
@Service
public class DriverService {
    @Resource
    private DriverDao driverDao;

    /**
     * 查询司机列表
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    public AppResponse listDriverByPage(DriverInfo driverInfo) {
        PageHelper.startPage(driverInfo.getPageNum(), driverInfo.getPageSize());
        List<DriverInfo> driverInfoList = driverDao.listDriverByPage(driverInfo);
        //包装Page对象
        PageInfo<DriverInfo> pageData = new PageInfo<DriverInfo>(driverInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 新增司机信息
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveDriver(DriverInfo driverInfo) {
        //检验账号是否存在
        int countUserAcct = driverDao.countUserAcct(driverInfo);
        if (0 != countUserAcct){
            return AppResponse.bizError("用户账号已存在，请重新输入");

        }
        driverInfo.setUserCode(StringUtil.getCommonCode(2));
        driverInfo.setIsDeleted(0);
        //新增司机
        int count = driverDao.saveDriver(driverInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        //新增司机信息到用户
        int count1 = driverDao.saveUser(driverInfo);
        if (0 == count1){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 删除司机
     * @author zhong
     * @date 2020-04-06
     * @param userCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String userCode, String userId) {
        List<String> listCode = Arrays.asList(userCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = driverDao.deleteDriver(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改司机信息
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriver(DriverInfo driverInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        // 修改司机信息
        int count = driverDao.updateDriver(driverInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 查询司机详情信息
     * @author zhong
     * @date 2020-04-06
     * @param userCode
     * @return
     */
    public AppResponse getDriverByUserCode(String userCode) {
        DriverInfo driverInfo = driverDao.getDriverByUserCode(userCode);
        return AppResponse.success("查询成功！",driverInfo);
    }
}
