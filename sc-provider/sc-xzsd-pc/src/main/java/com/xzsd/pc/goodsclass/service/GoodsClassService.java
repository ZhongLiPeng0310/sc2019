package com.xzsd.pc.goodsclass.service;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goodsclass.dao.GoodsClassDao;
import com.xzsd.pc.goodsclass.entity.GoodsClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实现类
 * @author zhong
 * @date 2020-03-27
 */
@Service
public class GoodsClassService {

    @Resource
    private GoodsClassDao goodsClassDao;

    /**
     * 新增商品分类
     * @author
     * @date zhong
     * @param goodsClassInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveGoodsClass(GoodsClassInfo goodsClassInfo) {
        goodsClassInfo.setClassCode(StringUtil.getCommonCode(2));
        goodsClassInfo.setIsDeleted(0);
        //新增商品分类
        int count = goodsClassDao.saveGoodsClass(goodsClassInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        else{
            return AppResponse.success("新增成功！");
        }
    }


    /**
     * 修改商品分类
     * @param goodsClassInfo
     * @return
     */
    public AppResponse updateGoodsClass(GoodsClassInfo goodsClassInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改商品分类
        int count = goodsClassDao.updateGoodsClass(goodsClassInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 查询商品分类列表
     * @author zhong
     * @date 2020-03-27
     * @param goodsClassInfo
     * @return
     */
    public AppResponse listGoodsClass(GoodsClassInfo goodsClassInfo) {
        List<GoodsClassInfo> goodsClassInfoList = goodsClassDao.listGoodsClass(goodsClassInfo);
        return AppResponse.success("查询成功", goodsClassInfoList);
    }
    /**
     * 查询分类详情
     * @param classCode
     * @author zhong
     * @date 2020-03-27
     * @return
     */
    public AppResponse getClassByClassCode(String classCode) {
        GoodsClassInfo goodsClassInfo = goodsClassDao.getClassByClassCode(classCode);
        return AppResponse.success("查询成功！",goodsClassInfo);
    }

    /**
     * 删除商品分类
     * @param goodsClassInfo
     * @author zhong
     * @date 2020-03-27
     * @return
     */
    public AppResponse deleteGoodsClass(GoodsClassInfo goodsClassInfo) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除商品分类
        int count = goodsClassDao.deleteGoodsClass(goodsClassInfo);
        if (0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试!");
        }
        return appResponse;
    }

}
