package com.xzsd.pc.hotGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.dictionary.entity.DictionaryInfo;
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
        //检验新增的商品是否存在热门商品中
        int countGoods =  hotGoodsDao.countGoods(hotGoodsInfo);
        if (0 != countGoods){
            return AppResponse.bizError("新增失败，商品已存在！");
        }
        //检验新增的热门商品的排序序号是否重复
        int countSort =  hotGoodsDao.countSort(hotGoodsInfo);
        if (0 != countSort){
            return AppResponse.bizError("新增失败，该排序号已存在！");
        }
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
        // 删除热门商品
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
        //检验新增的商品是否存在热门商品中
        int countGoods =  hotGoodsDao.countGoods(hotGoodsInfo);
        if (0 != countGoods){
            return AppResponse.bizError("修改失败，商品已存在！");
        }
        //检验新增的热门商品的排序序号是否重复
        int countSort =  hotGoodsDao.countSort(hotGoodsInfo);
        if (0 != countSort){
            return AppResponse.bizError("修改失败，该排序号已存在！");
        }
        //修改热门商品信息
        int count = hotGoodsDao.updateHotGoods(hotGoodsInfo);
        if (0 == count){
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 设置热门商品展示数量
     * @author zhong
     * @date 2020-04-10
     * @param dictionaryInfo
     * @return
     */
    public AppResponse saveShowNo(DictionaryInfo dictionaryInfo) {
        //检验展示商品数量是否存在
        int countShowNo = hotGoodsDao.countShowNo(dictionaryInfo);
        if (0 != countShowNo){
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            dictionaryInfo.setUpdateName(userId);
            int count = hotGoodsDao.updateShowNo(dictionaryInfo);
            if (0 == count){
                return AppResponse.success("设置失败");
            }
            return AppResponse.success("设置成功");
        }
        dictionaryInfo.setDicCode(StringUtil.getCommonCode(2));
        dictionaryInfo.setIsDeleted(0);
        //设置热门商品数量展示
        int count = hotGoodsDao.saveShowNo(dictionaryInfo);
        if (0 == count){
            return AppResponse.bizError("设置失败，请重试！");
        }
        return AppResponse.success("设置成功！");
    }

    /**
     * 查询热门商品展示数量
     * @author  zhong
     * @date 2020-04-10
     * @param dictionaryInfo
     * @return
     */
    public AppResponse getShowNo(DictionaryInfo dictionaryInfo) {
        List<DictionaryInfo> dictionaryInfoList = hotGoodsDao.getShowNo(dictionaryInfo);
        return AppResponse.success("查询成功！",dictionaryInfoList);
    }

    /**
     * 查询热门商品详情
     * @author zhong
     * @date 2020-04-17
     * @param hotCode
     * @return
     */
    public AppResponse getHotGoodsByHotCode(String hotCode) {
        HotGoodsInfo hotGoodsInfo = hotGoodsDao.getHotGoodsByHotCode(hotCode);
        return AppResponse.success("查询成功！",hotGoodsInfo);
    }
}
