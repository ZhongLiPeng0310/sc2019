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
        //检验新增的商品分类名称是否已存在
        int countClassName = goodsClassDao.countClassName(goodsClassInfo);
        if (0 != countClassName){
            return AppResponse.bizError("新增失败，新增的分类名称已存在！");
        }
        //新增商品分类
        int saveGoodsClass = goodsClassDao.saveGoodsClass(goodsClassInfo);
        if (0 == saveGoodsClass){
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
        //检验修改的商品分类名称是否已存在
        int countClassName = goodsClassDao.countClassName(goodsClassInfo);
        if (0 != countClassName){
            return AppResponse.bizError("新增失败，新增的分类名称已存在！");
        }
        //修改商品分类
        int updateGoodsClass = goodsClassDao.updateGoodsClass(goodsClassInfo);
        if (0 == updateGoodsClass) {
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
        //检验删除的商品分类是否存在二级分类
        int countSecondClass = goodsClassDao.countSecondClass(goodsClassInfo);
        if (0 == countSecondClass){
            return AppResponse.bizError("删除失败，删除的分类存在二级分类！");
        }
        //删除商品分类
        int deleteGoodsClass = goodsClassDao.deleteGoodsClass(goodsClassInfo);
        if (0 == deleteGoodsClass) {
            appResponse = AppResponse.bizError("删除失败，请重试!");
        }
        return appResponse;
    }

}
