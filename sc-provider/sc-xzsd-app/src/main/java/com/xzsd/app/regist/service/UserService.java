package com.xzsd.app.regist.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.regist.dao.UserDao;
import com.xzsd.app.regist.entity.UserInfo;
import com.xzsd.app.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 实现类
 * @author zhong
 * @date 2020-03-26
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    /**
     * 新增用户
     * @param userInfo
     * @return
     * @author zhong
     * @date 2020-03-26
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveUser(UserInfo userInfo){
        //检验账号是否存在
        int countUserAcct = userDao.countUserAcct(userInfo);
        if (0 != countUserAcct){
            return AppResponse.bizError("用户账号已存在，请重新输入");
        }
        //检验手机号码是否存在
        int countUserPhone = userDao.countUserPhone(userInfo);
        if (0 != countUserPhone){
            return AppResponse.bizError("用户手机号码已存在，请重新输入");
        }
//        //检验身份证号码是否存在
//        int countIdCard = userDao.countIdCard(userInfo);
//        if (0 != countIdCard){
//            return AppResponse.bizError("用户身份证已存在，请重新输入");
//        }
        //检验邮箱是否存在
        int countEmail = userDao.countEmail(userInfo);
        if (0 != countEmail){
            return AppResponse.bizError("用户邮箱已存在，请重新输入");
        }
        // 密码加密 默认为123
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        userInfo.setUserCode(StringUtil.getCommonCode(2));
        userInfo.setIsDeleted(0);
        //新增客户信息到用户表
        int count = userDao.saveUser(userInfo);
        //新增客户信息到客户表
        int countTnCustomer = userDao.saveUserToCus(userInfo);
        if (0 == count && 0 ==countTnCustomer){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }


}
