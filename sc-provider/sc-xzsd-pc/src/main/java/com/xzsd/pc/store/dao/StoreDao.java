package com.xzsd.pc.store.dao;


import com.xzsd.pc.dictionary.entity.DictionaryInfo;
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

    /**
     * 检验新增门店时用户编码是否已存在门店中
     * @author zhong
     * @date 2020-04-12
     * @param storeInfo
     * @return
     */
    int countUser(StoreInfo storeInfo);

    /**
     * 检验新增门店时营业执照编码是否已存在门店中
     * @author zhong
     * @date 2020-04-12
     * @param storeInfo
     * @return
     */
    int countLicense(StoreInfo storeInfo);

    /**
     * 检验新增的用户编码是否存在用户表中
     * @author zhong
     * @date 2020-04-12
     * @param storeInfo
     * @return
     */
    int countUserInUser(StoreInfo storeInfo);

    /**
     * 查询省份列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    List<DictionaryInfo> listProvince(DictionaryInfo dictionaryInfo);
    /**
     * 查询城市列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    List<DictionaryInfo> listCity(DictionaryInfo dictionaryInfo);
    /**
     * 查询区列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    List<DictionaryInfo> listArea(DictionaryInfo dictionaryInfo);
}
