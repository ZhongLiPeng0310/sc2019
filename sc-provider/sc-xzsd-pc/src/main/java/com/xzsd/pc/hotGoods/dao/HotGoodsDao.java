package com.xzsd.pc.hotGoods.dao;


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
}
