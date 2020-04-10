package com.xzsd.pc.hotGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.hotGoods.dao.HotGoodsDao;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 热门商品实现类
 * @author zhong
 * @date 2020-04-04
 */
@Service
public class HotGoodsService {

    @Resource
    private HotGoodsDao hotGoodsDao;
    /**
     * 新增热门商品
     * @author  zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveHotGoods(HotGoodsInfo hotGoodsInfo) {
        hotGoodsInfo.setHotCode(StringUtil.getCommonCode(2));
        hotGoodsInfo.setIsDeleted(0);
        //新增热门商品
        int count = hotGoodsDao.saveHotGoods(hotGoodsInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询热门商品列表 分页
     * @author zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listHotGoodsByPage(HotGoodsInfo hotGoodsInfo) {
        PageHelper.startPage(hotGoodsInfo.getPageNum(),hotGoodsInfo.getPageSize());
        List<HotGoodsInfo> hotGoodsInfoList = hotGoodsDao.listHotGoodsByPage(hotGoodsInfo);
        //包装Page对象
        PageInfo<HotGoodsInfo> pageData = new PageInfo<>(hotGoodsInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 删除热门商品
     * @param hotCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteHotGoods(String hotCode, String userId) {
        List<String> listCode = Arrays.asList(hotCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = hotGoodsDao.deleteHotGoods(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改热门商品信息
     * @param hotGoodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoods(HotGoodsInfo hotGoodsInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改热门商品信息
        int count = hotGoodsDao.updateHotGoods(hotGoodsInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }
}
