package com.xzsd.app.goods.entity;

import java.util.Date;
import java.util.List;

/**
 * 商品分类实体类
 * @author zhong
 * @date 2020-03-27
 */
public class GoodsClassInfo {
    /**
     * 分类编码
     */
    private String classCode;
    /**
     * 分类名称
     */
    private String className;
    /**
     * 一级分类编码
     */
    private String classFirstCode;
    /**
     * 一级分类名称
     */
    private String classFirstName;
    /**
     * 二级分类编码
     */
    private String classSecondCode;
    /**
     * 二级分类名称
     */
    private String classSecondName;
    /**
     * 上一级分类编码
     */
    private String lastClassCode;
    /**
     * 作废标记 0不删除 1删除
     */
    private int isDeleted;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人
     */
    private String updateName;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 版本号
     */
    private int version;

    /**
     *页码
     */
    private int pageSize;

    /**
     * 页数
     */
    private int pageNum;
    /**
     * 一级分类集合
     */
    private List<ClassFirstList> classFirstList;
    /**
     * 二级分类集合
     */
    private List<ClassSecondList> classSecondGoodsList;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassFirstCode() {
        return classFirstCode;
    }

    public void setClassFirstCode(String classFirstCode) {
        this.classFirstCode = classFirstCode;
    }

    public String getClassFirstName() {
        return classFirstName;
    }

    public void setClassFirstName(String classFirstName) {
        this.classFirstName = classFirstName;
    }

    public String getClassSecondCode() {
        return classSecondCode;
    }

    public void setClassSecondCode(String classSecondCode) {
        this.classSecondCode = classSecondCode;
    }

    public String getClassSecondName() {
        return classSecondName;
    }

    public void setClassSecondName(String classSecondName) {
        this.classSecondName = classSecondName;
    }

    public String getLastClassCode() {
        return lastClassCode;
    }

    public void setLastClassCode(String lastClassCode) {
        this.lastClassCode = lastClassCode;
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

    public List<ClassFirstList> getClassFirstList() {
        return classFirstList;
    }

    public void setClassFirstList(List<ClassFirstList> classFirstList) {
        this.classFirstList = classFirstList;
    }

    public List<ClassSecondList> getClassSecondGoodsList() {
        return classSecondGoodsList;
    }

    public void setClassSecondGoodsList(List<ClassSecondList> classSecondGoodsList) {
        this.classSecondGoodsList = classSecondGoodsList;
    }
}
