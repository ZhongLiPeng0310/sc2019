package com.xzsd.pc.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.dictionary.entity.DictionaryInfo;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.StoreInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 门店管理实现层
 * @author zhong
 * @date 2020-04-06
 */
@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;
    @Resource
    private CustomerDao customerDao;

    /**
     * 新增门店
     * @author zhong
     * @date 2020-04-06
     * @param storeInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveStore(StoreInfo storeInfo) {
        storeInfo.setStoreCode(StringUtil.getStoreCode(2));
        storeInfo.setInviteCode(StringUtil.getInviteCode(3));
        storeInfo.setIsDeleted(0);
        //检验新增门店时用户编码是否已存在门店中
        int countUser = storeDao.countUser(storeInfo);
        if (0 != countUser){
            return AppResponse.bizError("新增失败，该店长已存在！");
        }
        //检验新增门店时营业执照编码是否已存在门店中
        int countLicense = storeDao.countLicense(storeInfo);
        if (0 != countLicense){
            return AppResponse.bizError("新增失败，该营业执照已存在！");
        }
        //检验新增的用户编码是否存在用户表中
        int countUserInUser = storeDao.countUserInUser(storeInfo);
        if (0 == countUserInUser){
            return AppResponse.bizError("新增失败，不存在该店长！");
        }
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
        //查询当前登录人的的id
        String userId = SecurityUtils.getCurrentUserId();
        storeInfo.setUserId(userId);
        //查询当前登录人的角色
        String role = customerDao.getUserRole(userId);
        storeInfo.setRole(role);
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
        //检验新增门店时用户编码是否已存在门店中
        int countUser = storeDao.countUser(storeInfo);
        if (0 != countUser){
            return AppResponse.bizError("修改失败，该店长已存在！");
        }
        //检验新增门店时营业执照编码是否已存在门店中
        int countLicense = storeDao.countLicense(storeInfo);
        if (0 != countLicense){
            return AppResponse.bizError("修改失败，该营业执照已存在！");
        }
        //检验新增的用户编码是否存在用户表中
        int countUserInUser = storeDao.countUserInUser(storeInfo);
        if (0 == countUserInUser){
            return AppResponse.bizError("修改失败，不存在该店长！");
        }
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

    /**
     * 查询省份列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    public AppResponse listProvince(DictionaryInfo dictionaryInfo) {
        List<DictionaryInfo> listProvince = storeDao.listProvince(dictionaryInfo);
        return AppResponse.success("查询成功！",listProvince);
    }

    /**
     * 查询城市列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    public AppResponse listCity(DictionaryInfo dictionaryInfo) {
        List<DictionaryInfo> listCity = storeDao.listCity(dictionaryInfo);
        return AppResponse.success("查询成功！",listCity);
    }

    /**
     * 查询区域列表
     * @author zhong
     * @date 2020-04-13
     * @param dictionaryInfo
     * @return
     */
    public AppResponse listArea(DictionaryInfo dictionaryInfo) {
        List<DictionaryInfo> listArea = storeDao.listArea(dictionaryInfo);
        return AppResponse.success("查询成功！",listArea);
    }
}
