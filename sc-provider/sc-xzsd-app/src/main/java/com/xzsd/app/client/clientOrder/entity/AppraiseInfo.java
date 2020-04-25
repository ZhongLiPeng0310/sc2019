package com.xzsd.app.client.clientOrder.entity;

import java.io.Serializable;

public class AppraiseInfo implements Serializable {
    /**
     * 客户编码
     */
    private String userCode;
    /**
     * 评价编码
     */
    private String appraiseCode;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 评价星级
     */
    private String appraiseLevel;
    /**
     * 评价内容
     */
    private String appraiseDetail;
    /**
     * 商品平均星级
     */
    private String avgLevel;
    /**
     * 评价星级
     */
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDeleted;
    /**
     * 创建人
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
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAppraiseCode() {
        return appraiseCode;
    }

    public void setAppraiseCode(String appraiseCode) {
        this.appraiseCode = appraiseCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getAppraiseLevel() {
        return appraiseLevel;
    }

    public void setAppraiseLevel(String appraiseLevel) {
        this.appraiseLevel = appraiseLevel;
    }

    public String getAppraiseDetail() {
        return appraiseDetail;
    }

    public void setAppraiseDetail(String appraiseDetail) {
        this.appraiseDetail = appraiseDetail;
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

    public String getAvgLevel() {
        return avgLevel;
    }

    public void setAvgLevel(String avgLevel) {
        this.avgLevel = avgLevel;
    }
}
