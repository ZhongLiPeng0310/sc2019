package com.xzsd.pc.pictureShow.entity;

import java.util.Date;

/**
 * 轮播图实体类
 * @author zhong
 * @date 2020-04-05
 */
public class PictureShowInfo {
    /**
     *轮播图编码
     */
    private String showCode;
    /**
     * 轮播图排序号
     */
    private int sortNo;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 轮播图状态  0启用 1禁用
     */
    private int showState;
    /**
     *有效期开始
     */
    private String beginTime;
    /**
     * 有效期停止
     */
    private String endTime;
    /**
     * 作废标记 0保留  1删除
     */
    private int isDeleted;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateName;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 版本号
     */
    private String version;
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;

    public String getShowCode() {
        return showCode;
    }

    public void setShowCode(String showCode) {
        this.showCode = showCode;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
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

    public int getShowState() {
        return showState;
    }

    public void setShowState(int showState) {
        this.showState = showState;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
