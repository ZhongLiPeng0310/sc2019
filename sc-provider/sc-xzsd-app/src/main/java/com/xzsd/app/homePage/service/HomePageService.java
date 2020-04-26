package com.xzsd.app.homePage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.homePage.dao.HomePageDao;
import com.xzsd.app.homePage.entity.HotGoodsInfo;
import com.xzsd.app.homePage.entity.PictureShowInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomePageService {
    @Resource
    private HomePageDao homePageDao;

    /**
     * 查询首页轮播图
     * @author zhong
     * @date 2020-04-12
     * @param pictureShowInfo
     * @return
     */
    public AppResponse getPictureShow(PictureShowInfo pictureShowInfo) {
        List<PictureShowInfo> pictureShowInfoList = homePageDao.getPictureShow(pictureShowInfo);
        return AppResponse.success("查询成功！",pictureShowInfoList);
    }

    /**
     * 查询首页热门商品
     * @author zhong
     * @date 2020-04-12
     * @param hotGoodsInfo
     * @return
     */
    public AppResponse getHotGoods(HotGoodsInfo hotGoodsInfo) {
        //查询展示数量
        int showNo = homePageDao.getShowNo();
        List<HotGoodsInfo> hotGoodsInfoList = homePageDao.getHotGoods(showNo);
        return AppResponse.success("查询成功！",hotGoodsInfoList);
    }
}
