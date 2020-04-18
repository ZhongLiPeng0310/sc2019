package com.xzsd.pc.hotGoods.dao;


import com.xzsd.pc.dictionary.entity.DictionaryInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotGoodsDao {
    /**
     * 新增热门商品
     * @author zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    int saveHotGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 查询热门商品列表 分页
     * @author zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    List<HotGoodsInfo> listHotGoodsByPage(HotGoodsInfo hotGoodsInfo);

    /**
     * 删除热门商品
     * @author zhong
     * @date 2020-04-04
     * @param listCode
     * @param userId
     * @return
     */
    int deleteHotGoods(@Param("listCode") List<String> listCode, @Param("userId") String userId);

    /**
     * 修改热门商品信息
     * @author zhong
     * @date 2020-04-04
     * @param hotGoodsInfo
     * @return
     */
    int updateHotGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 设置热门商品展示数量
     * @param dictionaryInfo
     * @return
     */
    int saveShowNo(DictionaryInfo dictionaryInfo);

    /**
     * 查询热门商品展示数量
     * @param dictionaryInfo
     * @return
     */
    List<DictionaryInfo> getShowNo(DictionaryInfo dictionaryInfo);

    /**
     * 检验热门商品数量展示是否存在
     * @author zhong
     * @date 2020-04-10
     * @param dictionaryInfo
     * @return
     */
    int countShowNo(DictionaryInfo dictionaryInfo);

    /**
     * 更新热门商品展示数量
     * @author zhong
     * @date 2020-04-10
     * @param dictionaryInfo
     * @return
     */
    int updateShowNo(DictionaryInfo dictionaryInfo);

    /**
     * 检验新增的商品是否存在热门商品表中
     * @param hotGoodsInfo
     * @return
     */
    int countGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 检验新增的热门商品的排序序号是否重复
     * @author zhong
     * @date 2020-04-10
     * @param hotGoodsInfo
     * @return
     */
    int countSort(HotGoodsInfo hotGoodsInfo);

    /**
     * 查询热门商品详情
     * @author zhong
     * @date 2020-04-17
     * @param hotCode
     * @return
     */
    HotGoodsInfo getHotGoodsByHotCode(String hotCode);
}
