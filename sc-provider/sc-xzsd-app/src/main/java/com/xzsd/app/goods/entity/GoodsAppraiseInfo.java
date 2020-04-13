package com.xzsd.app.goods.entity;

import java.util.Date;

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
     * 客户编码
     */
    private String userCode;
    /**
     * 用户账号
     */
    private String userAcct;
    /**
     * 评价内容
     */
    private String appraiseDetail;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品图片
     */
    private String imagePath;
    /**
     * 评价等级(1-2星差评 3-4星中评 5好评)
     */
    private int appraiseLevel;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDeleted;
    /**
     * 评价状态 1已评价
     */
    private int appraiseState;
    /**
     * 创建者
     */
    private String createName;
    /**
     * 创建时间
     * @return
     */
    private Date createTime;
    /**
     * 更新人
     * @return
     */
    private String updateName;
    /**
     * 更新时间
     * @return
     */
    private Date updateTime;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getAppraiseLevel() {
        return appraiseLevel;
    }

    public void setAppraiseLevel(int appraiseLevel) {
        this.appraiseLevel = appraiseLevel;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getAppraiseState() {
        return appraiseState;
    }

    public void setAppraiseState(int appraiseState) {
        this.appraiseState = appraiseState;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
}
