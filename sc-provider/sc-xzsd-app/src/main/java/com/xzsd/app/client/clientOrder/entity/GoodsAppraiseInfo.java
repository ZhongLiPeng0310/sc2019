package com.xzsd.app.client.clientOrder.entity;

import java.util.Date;
import java.util.List;

/**
 * 商品评价实体类
 * @author zhong
 * @date 2020-04-13
 */
public class GoodsAppraiseInfo {
    /**
     * 评价编码
     */
    private String appraiseCode;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 客户编码
     */
    private String userCode;
    /**
     * 用户账号
     */
    private String userAcct;
    /**
     * 当前登录人id
     */
    private String userId;
    /**
     * 评价内容
     */
    private String appraiseDetail;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 图片编码
     */
    private String imageCode;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 图片序号
     */
    private int imageNum;
    /**
     * 评价等级(1-2星差评 3-4星中评 5好评)
     */
    private String appraiseLevel;
    /**
     * 评价评价星级
     */
    private String avgLevel;
    /**
     * 创建时间
     * @return
     */
    private Date createTime;
    /**
     * 图片集合
     */
    private List<ImageInfo> imageInfoList;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDeleted;
    /**
     * 创建者
     */
    private String createName;

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
    private int version;

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

    public String getAppraiseCode() {
        return appraiseCode;
    }

    public void setAppraiseCode(String appraiseCode) {
        this.appraiseCode = appraiseCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppraiseDetail() {
        return appraiseDetail;
    }

    public void setAppraiseDetail(String appraiseDetail) {
        this.appraiseDetail = appraiseDetail;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }

    public String getAppraiseLevel() {
        return appraiseLevel;
    }

    public void setAppraiseLevel(String appraiseLevel) {
        this.appraiseLevel = appraiseLevel;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
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

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageNum() {
        return imageNum;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAvgLevel() {
        return avgLevel;
    }

    public void setAvgLevel(String avgLevel) {
        this.avgLevel = avgLevel;
    }
}
