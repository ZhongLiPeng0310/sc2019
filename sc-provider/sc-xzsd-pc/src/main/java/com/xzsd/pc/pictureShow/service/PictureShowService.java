package com.xzsd.pc.pictureShow.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.sun.jersey.core.impl.provider.entity.XMLRootObjectProvider;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.pictureShow.dao.PictureShowDao;
import com.xzsd.pc.pictureShow.entity.PictureShowInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureShowService {

    @Resource
    private PictureShowDao pictureShowDao;
    /**
     * 新增轮播图
     * @author zhong
     * @date 2020-04-04
     * @param pictureShowInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse savePictureShow(PictureShowInfo pictureShowInfo) {
        //检验新增的商品、序号、图片是否存在轮播图中
        int countGoods =  pictureShowDao.countGoods(pictureShowInfo);
        if (0 != countGoods){
            return AppResponse.bizError("新增失败，商品或排序号或轮播图的图片已存在！请重试");
        }
//        //检验新增的轮播图序号是否重复
//        int countSort =  pictureShowDao.countSort(pictureShowInfo);
//        if (0 != countSort){
//            return AppResponse.bizError("新增失败，该排序号已存在！");
//        }
//        //检验新增的轮播图图片是否已新增
//        int countPicture = pictureShowDao.countPicture(pictureShowInfo);
//        if (0 != countPicture){
//            return AppResponse.bizError("新增失败，轮播图的图片已存在！");
//        }
        //随机轮播图编号
        pictureShowInfo.setShowCode(StringUtil.getCommonCode(2));
        pictureShowInfo.setIsDeleted(0);
        //新增轮播图
        int savePictureShow = pictureShowDao.savePictureShow(pictureShowInfo);
        if (0 == savePictureShow){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功!");
    }

    /**
     * 查询轮播图列表 分页
     * @author zhong
     * @date 2020-04-05
     * @param pictureShowInfo
     * @return
     */
    public AppResponse listPictureShowByPage(PictureShowInfo pictureShowInfo) {
        PageHelper.startPage(pictureShowInfo.getPageNum(),pictureShowInfo.getPageSize());
        List<PictureShowInfo> pictureShowInfoList = pictureShowDao.listPictureShowByPage(pictureShowInfo);
        //包装Page对象
        PageInfo<PictureShowInfo> pageData = new PageInfo<>(pictureShowInfoList);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * 删除轮播图
     * @author zhong
     * @date 2020-04-05
     * @param showCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deletePictureShow(String showCode, String userId) {
        List<String> listCode = Arrays.asList(showCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除轮播图
        int count = pictureShowDao.deletePictureShow(listCode,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改轮播图上架下架接口
     * @author zhong
     * @date 2020-04-05
     * @param showCode
     * @param showState
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updatePictureShowState(String showCode, int showState,String version,String userId) {
        List<String> listCode = Arrays.asList(showCode.split(","));
        List<String> listVersion = Arrays.asList(version.split(","));
        AppResponse appResponse = AppResponse.success("修改成功");
        //修改轮播图上架下架
        int count = pictureShowDao.updatePictureShowState(listCode,showState,listVersion,userId);
        if (0 == count){
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 连接商品列表
     * @param goodsInfo
     * @return
     */
    public AppResponse listGoodsByPage(GoodsInfo goodsInfo) {
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfoList = pictureShowDao.listGoodsByPage(goodsInfo);
        //包装Page对象
        PageInfo<GoodsInfo> pageData= new PageInfo<GoodsInfo>(goodsInfoList);
        return AppResponse.success("查询列表成功！", pageData);
    }
}
