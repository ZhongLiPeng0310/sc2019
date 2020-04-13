package com.xzsd.app.goods.dao;

import com.xzsd.app.goods.entity.GoodsAppraiseInfo;
import com.xzsd.app.goods.entity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
    /**
     * app查询商品详情
     * @author zhong
     * @date 2020-04-13
     * @param goodsCode
     * @return
     */
    GoodsInfo getGoods(@Param("goodsCode") String goodsCode);



    /**
     * app查询商品评价列表
     * @author zhong
     * @date 2020-04-13
     * @param goodsAppraiseInfo
     * @return
     */
    List<GoodsAppraiseInfo> getGoodsAppraise(GoodsAppraiseInfo goodsAppraiseInfo);
}
