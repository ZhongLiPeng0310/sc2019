package com.xzsd.app.homePage.dao;

import com.xzsd.app.homePage.entity.HotGoodsInfo;
import com.xzsd.app.homePage.entity.PictureShowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param showNo
     * @return
     */
    List<HotGoodsInfo> getHotGoods(@Param("showNo") int showNo);

    /**
     * 获取展示数量
     * @author zhong
     * @date 2020-04-26
     * @return
     */
    int getShowNo();


}
