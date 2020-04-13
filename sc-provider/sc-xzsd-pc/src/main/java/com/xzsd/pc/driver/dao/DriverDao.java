package com.xzsd.pc.driver.dao;


import com.xzsd.pc.driver.entity.DriverInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriverDao {

    /**
     * 判断账号是否存在
     * @param driverInfo
     * @return
     */
     int countUserAcct(DriverInfo driverInfo);
    /**
     * 新增司机
     * @param driverInfo
     * @return
     */
    int saveDriver(DriverInfo driverInfo);
    /**
     * 新增司机信息到用户表
     * @param driverInfo
     * @return
     */
    int saveUser(DriverInfo driverInfo);
    /**
     * 查询司机列表  分页
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    List<DriverInfo> listDriverByPage(DriverInfo driverInfo);

    /**
     * 删除司机信息
     * @author zhong
     * @date 2020-04-06
     * @param listCode
     * @param userId
     * @return
     */
    int deleteDriver(@Param("listCode") List<String> listCode,@Param("userId") String userId);

    /**
     * 删除在用户表的司机信息
     * @author zhong
     * @date 2020-04-10
     * @param listCode
     * @param userId
     * @return
     */
    int deleteUser(@Param("listCode") List<String> listCode,@Param("userId") String userId);
    /**
     * 修改司机信息
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    int updateDriver(DriverInfo driverInfo);

    /**
     * 修改在用户表的司机信息
     * @param driverInfo
     * @return
     */
    int updateUserDriver(DriverInfo driverInfo);
    /**
     * 查询司机详情信息
     * @author zhong
     * @date 2020-04-06
     * @param userCode
     * @return
     */
    DriverInfo getDriverByUserCode(@Param("userCode")String userCode);

    /**
     * 检验新增司机时 手机号码是否已存在
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    int countUserPhone(DriverInfo driverInfo);
}
