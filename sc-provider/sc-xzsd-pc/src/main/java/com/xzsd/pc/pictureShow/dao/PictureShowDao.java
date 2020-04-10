package com.xzsd.pc.pictureShow.dao;


import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.pictureShow.entity.PictureShowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PictureShowDao {
    /**
     * 新增轮播图
     * @author zhong
     * @date 2020-04-05
     * @param pictureShowInfo
     * @return
     */

    int savePictureShow(PictureShowInfo pictureShowInfo);

    /**
     * 查询轮播图列表  分页
     * @author zhong
     * @date 2020-04-05
     * @param pictureShowInfo
     * @return
     */
    List<PictureShowInfo> listPictureShowByPage(PictureShowInfo pictureShowInfo);

    /**
     * 删除轮播图
     * @author zhong
     * @date 2020-04-05
     * @param listCode
     * @param userId
     * @return
     */
    int deletePictureShow(@Param("listCode") List<String> listCode,@Param("userId") String userId);

    /**
     * 修改轮播图启用禁用状态接口
     * @author zhong
     * @date 2020-04-05
     * @param listCode
     * @param showState
     * @param userId
     * @return
     */
    int updatePictureShowState(@Param("listCode")List<String> listCode,@Param("showState") int showState,@Param("userId") String userId);

    /**
     * 连接商品列表
     * @param goodsInfo
     * @return
     */
    List<GoodsInfo> listGoodsByPage(GoodsInfo goodsInfo);
}
