package com.xzsd.app.client.clientOrder.entity;

import java.io.Serializable;
import java.util.List;

public class AppraiseOrderInfo implements Serializable {
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 客户编码
     */
    private String userCode;
    /**
     * 商品的平均星级
     */
    private String avgLevel;
    /**
     * 评价信息
     */
    private List<AppraiseInfo> appraiseInfoList;
    /**
     * 商品信息
     */
    private List<GoodsInfo> goodsInfoList;
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<AppraiseInfo> getAppraiseInfoList() {
        return appraiseInfoList;
    }

    public void setAppraiseInfoList(List<AppraiseInfo> appraiseInfoList) {
        this.appraiseInfoList = appraiseInfoList;
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

    public List<GoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<GoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

}
