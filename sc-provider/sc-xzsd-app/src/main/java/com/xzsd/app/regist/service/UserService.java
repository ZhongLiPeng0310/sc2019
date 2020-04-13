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
        // 密码加密 默认为123456
        String password = PasswordUtils.generatePassword("123456");
        userInfo.setUserPassword(password);
        userInfo.setUserCode(StringUtil.getCommonCode(2));
        userInfo.setIsDeleted(0);
        //新增用户
        int count = userDao.saveUser(userInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }


}