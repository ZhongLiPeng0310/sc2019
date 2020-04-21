package com.xzsd.pc.store.entity;

import java.util.Date;

/**
 * 门店实体类
 * @author zhong
 * @date 2020-04-06
 */
public class StoreInfo {
    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店电话
     */
    private String phone;
    /**
     * 门店邀请码
     */
    private String inviteCode;
    /**
     * 店长编码
     */
    private String userCode;
    /**
     * 店长名称
     * @return
     */
    private String userName;

    /**
     * 店长账号
     * @return
     */
    private String userAcct;
    /**
     * 营业执照
     */
    private String licenseCode;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 市编码
     */
    private String cityCode;
    /**
     * 市级名称
     */
    private String cityName;
    /**
     * 区编码
     */
    private String areaCode;
    /**
     * 区级名称
     */
    private String areaName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 权限  角色0管理员查全部
     * 2店长只能查询自己的门店信息
     */
    private String role;
    /**
     * 当前登录人的id
     */
    private String userId;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDeleted;
    /**
     * 创建者
     */
    private String createName;
    /**
     * 创建时间
     * @return
     */
    private String createTime;
    /**
     * 更新人
     * @return
     */
    private String updateName;
    /**
     * 更新时间
     * @return
     */
    private String updateTime;
    /**
     * 版本号
     * @return
     */
    private String version;

    /**
     * 页码
     * @return
     */
    private int pageSize;

    /**
     * 页数
     * @return
     */
    private int pageNum;


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }
}
