package com.xzsd.pc.goods.dao;



import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goodsclass.entity.GoodsClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName GoodsDao
 * @Description goodsInfo
 * @Author zhong
 * @Date 2020-03-24
 */

/**
 * @Mapper  mybaist配置  跟数据库有关
 */
@Mapper
public interface GoodsDao {
    /**
     * 新增商品
     * @param goodsInfo 用户信息
     * @return
     */
    int saveGoods(GoodsInfo goodsInfo);

    /**
     * 获取商品列表
     * @param goodsInfo 商品信息
     * @return 所有商品信息
     */
    List<GoodsInfo> listGoodsByPage(GoodsInfo goodsInfo);
    /**
     * 修改商品信息
     * @param goodsInfo 商品信息
     * @return 修改结果
     */
    int updateGoods(GoodsInfo goodsInfo);

    /**
     * 检验删除的商品是否存在轮播图中
     * @author zhong
     * @date 2020-04-11
     * @param listCode
     * @return
     */
    int findGoodsPicShow(@Param("listCode") List<String> listCode);

    /**
     * 检验检验删除的商品是否存在热门商品中
     * @author zhong
     * @date 2020-04-11
     * @param listCode
     * @return
     */
    int findGoodsHotGoods(@Param("listCode") List<String> listCode);
    /**
     * 删除商品信息信息
     * @param listCode 选中的商品编号集合
     * @param
     * @return
     */
    int deleteGoods(@Param("listCode") List<String> listCode, @Param("userId") String userId);

    /**
     * 修改商品上架下架
     * @param listCode
     * @param
     * @param goodsState
     * @return
     */
    int updateGoodsState(@Param("listCode") List<String> listCode,@Param("goodsState") int goodsState,@Param("userId") String userId);
    /**
     * 查看商品信息
     * goodInfo
     * zhong
     * 2020-03-25
     */
    GoodsInfo getGoodsByGoodsCode(@Param("goodsCode") String goodsCode);

    /**
     * 查询商品一级分类接口
     * @param goodsClassInfo
     * @return
     */
    List<GoodsClassInfo> getFirstClass(GoodsClassInfo goodsClassInfo);

    /**
     * 查询商品二级分类接口
     * @author zhong
     * @date 2020-03-26
     * @param goodsClassInfo
     * @return
     */
    List<GoodsClassInfo> getSecondClass(GoodsClassInfo goodsClassInfo);



}
