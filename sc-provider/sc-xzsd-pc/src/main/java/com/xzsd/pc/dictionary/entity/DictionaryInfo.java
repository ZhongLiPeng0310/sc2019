package com.xzsd.pc.dictionary.entity;

import java.util.Date;

public class DictionaryInfo {
    /**
     * 字典编码
     */
    private String dicCode;
    /**
     * 字典名称
     */
    private String dicName;
    /**
     * 展示商品数量
     */
    private int showNo;
    /**
     * 标记  0为热门商品展示数量
     */
    private int mark;
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

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public int getShowNo() {
        return showNo;
    }

    public void setShowNo(int showNo) {
        this.showNo = showNo;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
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
}
