package com.xzsd.pc.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.StoreInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;

    /**
     * 新增门店
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveStore(StoreInfo storeInfo) {
        storeInfo.setStoreCode(StringUtil.getCommonCode(2));
        storeInfo.setIsDeleted(0);
        //新增门店
        int count = storeDao.saveStore(storeInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功!");
    }

    /**
     * 查询门店列表  分页
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    public AppResponse listStoreByPage(StoreInfo storeInfo) {
        PageHelper.startPage(storeInfo.getPageNum(),storeInfo.getPageSize());
        List<StoreInfo> storeInfoList = storeDao.listStoreByPage(storeInfo);
        //包装Page对象
        PageInfo<StoreInfo> pageData = new PageInfo<>(storeInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 删除门店
     * @author zhong
     * @date 2020-04-06
     * @param storeCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeCode, String userId) {
        List<String> listCode = Arrays.asList(storeCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //删除门店
        int count = storeDao.deleteStore(listCode,userId);
        if (0 == count){
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改门店信息
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStore(StoreInfo storeInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        int count = storeDao.updateStore(storeInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 查询门店详细信息
     * @author zhong
     * @date 2020-04-06
     * @param storeCode
     * @return
     */
    public AppResponse getStoreByStoreCode(String storeCode) {
        StoreInfo storeInfo = storeDao.getStoreByStoreCode(storeCode);
        return AppResponse.success("查询成功！",storeInfo);
    }
}
