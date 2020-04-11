package com.xzsd.pc.driver.contoller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.driver.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 司机管理 增删改查
 * @author zhong
 * @date 2020-04-06
 */
@RestController
@RequestMapping("/driver")
public class DriverController {
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);
    @Resource
    private DriverService driverService;
    /**
     * 新增司机
     * @author zhong
     * @date 2020-04-06
     */
    @PostMapping("saveDriver")
    public AppResponse saveDriver(DriverInfo driverInfo){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            driverInfo.setCreateName(userId);
            AppResponse appResponse = driverService.saveDriver(driverInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("用户新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询用户列表  分页
     * @param driverInfo
     * @return
     */
    @PostMapping(value = "listDriverByPage")
    public AppResponse listDriverByPage(DriverInfo driverInfo){
        try{
            return driverService.listDriverByPage(driverInfo);
        }catch (Exception e){
            logger.error("查询用户列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除司机信息
     * @author zhong
     * @date 2020-04-06
     * @param userCode
     * @return
     */
    @PostMapping("deleteDriver")
    public AppResponse deleteDriver(String userCode){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return driverService.deleteDriver(userCode,userId);
        } catch (Exception e) {
            logger.error("用户删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改司机信息
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    @PostMapping("updateDriver")
    public AppResponse updateDriver(DriverInfo driverInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            driverInfo.setCreateName(userId);
            driverInfo.setUpdateName(userId);
            return driverService.updateDriver(driverInfo);
        } catch (Exception e) {
            logger.error("修改司机信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机详情信息
     * @author zhong
     * @date 2020-04-06
     * @param userCode
     * @return
     */
    @PostMapping("getDriverByUserCode")
    public AppResponse getDriverByUserCode(String userCode){
        try{
            return driverService.getDriverByUserCode(userCode);
        }catch (Exception e){
            logger.error("用户查询错误");
            System.out.println(e.toString());
            throw e;
        }
    }
}
