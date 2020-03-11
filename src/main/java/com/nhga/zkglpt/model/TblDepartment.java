package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblDepartment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 自增列
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门编号
     */
    private String departCode;

    /**
     * 部门路径
     */
    private String departPath;

    /**
     * 上级部门编号
     */
    private String parentCode;

    /**
     * 部门名称
     */
    private String departName;

    /**
     * 部门层级
     */
    private Integer level;

    /**
     * 是否是叶子节点
     */
    private String leaf;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime modifyDate;

    /**
     * 删除标识：Y：是；N：否
     */
    private String isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getDepartPath() {
        return departPath;
    }

    public void setDepartPath(String departPath) {
        this.departPath = departPath;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TblDepartment{" +
        "id=" + id +
        ", departCode=" + departCode +
        ", departPath=" + departPath +
        ", parentCode=" + parentCode +
        ", departName=" + departName +
        ", level=" + level +
        ", leaf=" + leaf +
        ", creator=" + creator +
        ", modifier=" + modifier +
        ", createDate=" + createDate +
        ", modifyDate=" + modifyDate +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
