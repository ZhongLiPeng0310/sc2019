package com.xzsd.app.goods.service;

import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.goods.dao.GoodsDao;
import com.xzsd.app.goods.entity.GoodsAppraiseInfo;
import com.xzsd.app.goods.entity.GoodsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;

    /**
     * app查询商品详情
     * @author zhong
     * @date 2020-04-13
     * @param goodsCode
     * @return
     */
    public AppResponse getGoods(String goodsCode) {
        GoodsInfo goodsInfo = goodsDao.getGoods(goodsCode);
        return AppResponse.success("查询成功！",goodsInfo);
    }

    /**
     * app查询商品评价列表
     * @author zhong
     * @date 2020-04-13
     * @param goodsAppraiseInfo
     * @return
     */
    public AppResponse getGoodsAppraise(GoodsAppraiseInfo goodsAppraiseInfo) {
        PageHelper.startPage(goodsAppraiseInfo.getPageNum(),goodsAppraiseInfo.getPageSize());
        List<GoodsAppraiseInfo> goodsAppraiseInfoList = goodsDao.getGoodsAppraise(goodsAppraiseInfo);
        //包装Page对象
        PageInfo<GoodsAppraiseInfo> pageData = new PageInfo<>(goodsAppraiseInfoList);
        return AppResponse.success("查询成功！",pageData);
    }
}
