package com.xzsd.pc.driver.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

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
    @Resource
    private CustomerDao customerDao;

    /**
     * 查询司机列表
     * @author zhong
     * @date 2020-04-06
     * @param driverInfo
     * @return
     */
    public AppResponse listDriverByPage(DriverInfo driverInfo) {
        //查询当前登录人的的id
        String userId = SecurityUtils.getCurrentUserId();
        driverInfo.setUserId(userId);
        //查询当前登录人的角色
        int role = customerDao.getUserRole(userId);
        driverInfo.setRole(role);
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
        //检验手机号码是否存在
        int countDriverPhone = driverDao.countUserPhone(driverInfo);
        if (0 != countDriverPhone){
            return AppResponse.bizError("用户手机号码已存在，请重新输入");
        }
        //检验身份证号码是否存在
        int countIdCard = driverDao.countIdCard(driverInfo);
        if (0 != countIdCard){
            return AppResponse.bizError("用户身份证已存在，请重新输入");
        }
        //检验邮箱是否存在
        int countEmail = driverDao.countEmail(driverInfo);
        if (0 != countEmail){
            return AppResponse.bizError("用户邮箱已存在，请重新输入");
        }
        //加密密码
        String password = PasswordUtils.generatePassword(driverInfo.getUserPassword());
        driverInfo.setUserPassword(password);
        driverInfo.setUserCode(StringUtil.getCommonCode(2));
        driverInfo.setIsDeleted(0);
        //新增司机
        int saveToDriver = driverDao.saveDriver(driverInfo);
        if (0 == saveToDriver){  
            return AppResponse.bizError("新增失败，请重试！");
        }
        //新增司机信息到用户
        int saveToUser = driverDao.saveUser(driverInfo);
        if (0 == saveToUser){
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
        // 删除司机
        int deleteDriver = driverDao.deleteDriver(listCode,userId);
        //删除在用户表的司机信息
        int deleteUser = driverDao.deleteUser(listCode,userId);
        if(0 == deleteDriver && 0 == deleteUser) {
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
        //检验账号是否存在
        int countUserAcct = driverDao.countUserAcct(driverInfo);
        if (0 != countUserAcct){
            return AppResponse.bizError("用户账号已存在，请重新输入");
        }
        //检验手机号码是否存在
        int countDriverPhone = driverDao.countUserPhone(driverInfo);
        if (0 != countDriverPhone){
            return AppResponse.bizError("用户手机号码已存在，请重新输入");
        }
        //检验身份证号码是否存在
        int countIdCard = driverDao.countIdCard(driverInfo);
        if (0 != countIdCard){
            return AppResponse.bizError("用户身份证已存在，请重新输入");
        }
        //检验邮箱是否存在
        int countEmail = driverDao.countEmail(driverInfo);
        if (0 != countEmail){
            return AppResponse.bizError("用户邮箱已存在，请重新输入");
        }
        //加密密码
        String password = PasswordUtils.generatePassword(driverInfo.getUserPassword());
        driverInfo.setUserPassword(password);
        // 修改司机在用户表的信息
        int updateUserDriver = driverDao.updateUserDriver(driverInfo);
        // 修改司机信息
        int count = driverDao.updateDriver(driverInfo);
        if (0 == count && 0 == updateUserDriver) {
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
