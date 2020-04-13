package com.xzsd.pc.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.sun.jersey.core.impl.provider.entity.XMLRootObjectProvider;
import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goodsclass.entity.GoodsClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @DescriptionDemo 实现类
 * @Author zhong
 * @Date 2020-03-24
 */
@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;


    /**
     * addGoods 新增商品
     * @param goodsInfo
     * @return
     * @Author zhong
     * @Date 2020-03-24
     */
    /**
     * @Transactional  数据库回滚  数据有改动时 如果方法出现错误，数据库会回滚改动的数据不变
     * @param goodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveGoods(GoodsInfo goodsInfo) {
        /**
         * 随机生成商品编码
         */
        goodsInfo.setGoodsCode(StringUtil.getCommonCode(2));
        goodsInfo.setIsDeleted(0);
        // 校验新增商品时，isbn书号是否已存在
        int countIsbn =goodsDao.countIsbn(goodsInfo);
        if (0 != countIsbn){
            return AppResponse.bizError("新增失败，isbn书号已存在！");
        }
        // 新增商品
        int saveToGoods = goodsDao.saveGoods(goodsInfo);
        if(0 == saveToGoods) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询商品列表
     * @param goodsInfo
     * @return
     * @author zhong
     * @date 2020-03-25
     */
    public AppResponse listGoodsByPage(GoodsInfo goodsInfo) {
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfoList = goodsDao.listGoodsByPage(goodsInfo);
        //包装Page对象
        PageInfo<GoodsInfo> pageData= new PageInfo<GoodsInfo>(goodsInfoList);
        return AppResponse.success("查询列表成功！", pageData);
    }


    /**
     * 修改商品
     * @return
     * @author zhong
     * 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoods(GoodsInfo goodsInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        goodsInfo.setGoodsCode(goodsInfo.getGoodsCode());
        goodsInfo.setVersion(goodsInfo.getVersion());
        goodsInfo.setGoodsName(goodsInfo.getGoodsName());
        goodsInfo.setIsbn(goodsInfo.getIsbn());
        goodsInfo.setClassCode(goodsInfo.getGoodsCode());
        goodsInfo.setGoodsIntroduce(goodsInfo.getGoodsIntroduce());
        goodsInfo.setAdvertisement(goodsInfo.getAdvertisement());
        goodsInfo.setStoreCode(goodsInfo.getStoreCode());
        goodsInfo.setStock(goodsInfo.getStock());
        goodsInfo.setCostPrice(goodsInfo.getCostPrice());
        goodsInfo.setSalePrice(goodsInfo.getSalePrice());
        goodsInfo.setImageurl(goodsInfo.getImageurl());
        // 校验修改商品时，isbn书号是否已存在
        int countIsbn =goodsDao.countIsbn(goodsInfo);
        if (0 != countIsbn){
            return AppResponse.bizError("新增失败，isbn书号已存在！");
        }
        //修改商品信息
        int updateGood = goodsDao.updateGoods(goodsInfo);
        if (0 == updateGood){
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 删除商品
     * goodsCode
     * zhong
     * 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsCode,String userId){
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //检验检验删除的商品是否存在轮播图中
        int countPicShow = goodsDao.findGoodsPicShow(listCode);
        if (0 != countPicShow){
            return AppResponse.bizError("删除的商品存在轮播图中,删除失败！");
        }
        //检验检验删除的商品是否存在热门商品中
        int countHotGoods = goodsDao.findGoodsHotGoods(listCode);
        if (0 != countHotGoods){
            return AppResponse.bizError("删除的商品存在热门商品中,删除失败！");
        }
        //删除商品
        int deleteGood = goodsDao.deleteGoods(listCode,userId);
        if (0 == deleteGood){
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * //修改商品上架下架
     * @param goodsCode
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsState(String goodsCode,int goodsState,String userId) {
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改商品上架下架
        int updateState = goodsDao.updateGoodsState(listCode,goodsState,userId);
        if (0 == updateState){
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }
    /**
     * 查询商品详情
     * return
     * zhong
     * 2020-03-25
     */
    public AppResponse getGoodsByGoodsCode(String goodsCode){
        GoodsInfo goodsInfo = goodsDao.getGoodsByGoodsCode(goodsCode);
        return AppResponse.success("查询成功！",goodsInfo);
    }

    /**
     * 查询一级分类接口
     * @date 2020-03-26
     * @author zhong
     * @param goodsClassInfo
     * @return
     */
    public AppResponse getFirstClass(GoodsClassInfo goodsClassInfo) {
        List<GoodsClassInfo> classFirstList = goodsDao.getFirstClass(goodsClassInfo);
        return AppResponse.success("查询列表成功！", classFirstList);
    }
    /**
     * 查询一级分类接口
     * @date 2020-03-26
     * @author zhong
     * @param goodsClassInfo
     * @return
     */
    public AppResponse getSecondClass(GoodsClassInfo goodsClassInfo) {
        List<GoodsClassInfo> classSecondList = goodsDao.getSecondClass(goodsClassInfo);
        return AppResponse.success("查询列表成功！", classSecondList);
    }
}
