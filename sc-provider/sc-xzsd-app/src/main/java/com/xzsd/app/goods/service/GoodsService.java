package com.xzsd.app.goods.service;

import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.goods.dao.GoodsDao;
import com.xzsd.app.goods.entity.GoodsAppraiseInfo;
import com.xzsd.app.goods.entity.GoodsClassInfo;
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
     * 查询二级分类接口
     * @date 2020-03-26
     * @author zhong
     * @param lastClassCode
     * @return
     */
    public AppResponse getSecondClass(String lastClassCode) {
        List<GoodsClassInfo> classSecondList = goodsDao.getSecondClass(lastClassCode);
        return AppResponse.success("查询列表成功！", classSecondList);
    }

    /**
     * 新增订单评价
     * @author zhong
     * @date 2002-04-19
     * @param goodsAppraiseInfo
     * @return
     */
    public AppResponse saveOrdersAppraise(GoodsAppraiseInfo goodsAppraiseInfo) {
        String userId = SecurityUtils.getCurrentUserId();
        goodsAppraiseInfo.setUserId(userId);
        goodsAppraiseInfo.setIsDeleted(0);
        goodsAppraiseInfo.setCreateName(userId);
        int saveOrdersAppraise = goodsDao.saveOrdersAppraise(goodsAppraiseInfo);
        if (0 == saveOrdersAppraise){
            return AppResponse.bizError("新增失败！");
        }
        return AppResponse.success("新增成功！");
    }
}
