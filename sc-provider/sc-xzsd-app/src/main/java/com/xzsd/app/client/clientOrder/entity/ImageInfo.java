package com.xzsd.app.client.clientOrder.entity;

import java.util.List;

/**
 * 图片实体类
 * @author zhong
 * @date 2020-04-13
 */
public class ImageInfo {
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
     * 商品编码
     */
    private String goodsCode;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 当前登录人的id
     */
    private String userId;
    /**
     * 图片集合
     */
    private List<GoodsAppraiseInfo> goodsAppraiseInfos;
    /**
     * 用户编码
     */
    private String userCode;
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

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<GoodsAppraiseInfo> getGoodsAppraiseInfos() {
        return goodsAppraiseInfos;
    }

    public void setGoodsAppraiseInfos(List<GoodsAppraiseInfo> goodsAppraiseInfos) {
        this.goodsAppraiseInfos = goodsAppraiseInfos;
    }
}
