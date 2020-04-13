package com.xzsd.app.homePage.dao;

import com.xzsd.app.homePage.entity.HotGoodsInfo;
import com.xzsd.app.homePage.entity.PictureShowInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface HomePageDao {
    /**
     * 查询首页轮播图
     * @author zhong
     * @date 2020-04-12
     * @param pictureShowInfo
     * @return
     */
    List<PictureShowInfo> getPictureShow(PictureShowInfo pictureShowInfo);

    /**
     * 查询首页热门商品
     * @author zhong
     * @date 2020-04-12
     * @param hotGoodsInfo
     * @return
     */
    List<HotGoodsInfo> getHotGoods(HotGoodsInfo hotGoodsInfo);
}
