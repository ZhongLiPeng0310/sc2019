package com.xzsd.pc.store.dao;


import com.xzsd.pc.store.entity.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

@Mapper
public interface StoreDao {
    /**
     * 新增门店信息
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    int saveStore(StoreInfo storeInfo);

    /**
     * 查询门店列表  分页
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    List<StoreInfo> listStoreByPage(StoreInfo storeInfo);

    /**
     * 删除门店信息
     * @author zhong
     * @date 2020-04-06
     * @param listCode
     * @param userId
     * @return
     */
    int deleteStore(@Param("listCode") List<String> listCode, @Param("userId") String userId);

    /**
     * 修改门店信息
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */

    int updateStore(StoreInfo storeInfo);

    /**
     * 查询门店详情信息
     * @author zhong
     * @date 2020-04-06
     * @param storeCode
     * @return
     */
    StoreInfo getStoreByStoreCode(@Param("storeCode") String storeCode);
}
