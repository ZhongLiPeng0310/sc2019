package com.neusoft.webauth.menu.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.neusoft.webauth.menu.dao.MenuDao;
import com.neusoft.webauth.menu.entity.MenuInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单的实现层
 * @author zhong
 * @date 2020-04-11
 */
@Service
public class MenuService {
    @Resource
    private MenuDao menuDao;

    /**
     * 新增菜单
     * @author zhong
     * @date 2020-04-11
     * @param menuInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveMenu(MenuInfo menuInfo) {
        menuInfo.setMenuCode(StringUtil.getCommonCode(2));
        menuInfo.setIsDeleted(0);
        //新增菜单
        int count = menuDao.saveMenu(menuInfo);
        if (0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询菜单列表
     * @author zhong
     * @date 2020-04-11
     * @param menuInfo
     * @return
     */
    public AppResponse listMenus(MenuInfo menuInfo) {
        List<MenuInfo> menuInfoList = menuDao.listMenus(menuInfo);
        return AppResponse.success("查询成功！",menuInfoList);
    }

    /**
     * 修改菜单
     * @author zhong
     * @date 2020-04-11
     * @param menuInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateMenu(MenuInfo menuInfo) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        //修改菜单
        int count = menuDao.updateMenu(menuInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 删除菜单
     * @author zhong
     * @date 2020-04-11
     * @param menuCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteMenu(String menuCode, String userId) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除菜单
        int count = menuDao.deleteMenu(menuCode,userId);
        if (0 == count){
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询菜单详情
     * @author zhong
     * @date 2020-04-11
     * @param menuCode
     * @return
     */
    public AppResponse getMenuByCode(String menuCode) {
        MenuInfo menuInfo = menuDao.getMenuByCode(menuCode);
        return AppResponse.success("查询成功！",menuInfo);
    }
}
