package com.xzsd.pc.goodsclass.dao;



import com.xzsd.pc.goodsclass.entity.classFirstList;
import com.xzsd.pc.goodsclass.entity.GoodsClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsClassDao {
    /**
     * 新增商品分类
     * @author zhong
     * @date 2020-03-27
     * @param goodsClassInfo
     * @return
     */
    int saveGoodsClass(GoodsClassInfo goodsClassInfo);

    /**
     * 修改商品分类
     * @param goodsClassInfo
     * @return
     */
    int updateGoodsClass(GoodsClassInfo goodsClassInfo);

    /**
     * 查询分类详情
     * @author
     * @date 2020-03-27
     * @param classCode
     * @return
     */
    GoodsClassInfo getClassByClassCode(@Param("classCode") String classCode);

    /**
     * 查询商品分类列表
     * @author zhong
     * @date 2020-03-27
     * @param classFirstList
     * @return
     */
    List<classFirstList> listGoodsClass(classFirstList classFirstList);

    /**
     * 删除商品分类
     * @author zhong
     * @date 2020-03-27
     * @param goodsClassInfo
     * @return
     */
    int deleteGoodsClass(GoodsClassInfo goodsClassInfo);

}
