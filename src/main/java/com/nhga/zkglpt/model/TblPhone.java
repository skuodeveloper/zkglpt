package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 二手手机表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblPhone implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String xh;

    /**
     * 串号
     */
    private String ch;

    /**
     * 颜色
     */
    private String color;

    /**
     * 价值
     */
    private String jz;

    /**
     * 收购时间
     */
    private String sgsj;

    /**
     * 物品照片
     */
    private String wpzp;

    /**
     * 可疑信息
     */
    private String kyxx;

    /**
     * 店员ID
     */
    private Integer dyId;

    /**
     * 录入时间
     */
    private Date lrsj;

    /**
     * 创建者姓名
     */
    private String creator;

    /**
     * 更新者姓名
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date modifyDate;

    /**
     * Y表示逻辑删除，N表示可用
     */
    private String isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getJz() {
        return jz;
    }

    public void setJz(String jz) {
        this.jz = jz;
    }

    public String getSgsj() {
        return sgsj;
    }

    public void setSgsj(String sgsj) {
        this.sgsj = sgsj;
    }

    public String getWpzp() {
        return wpzp;
    }

    public void setWpzp(String wpzp) {
        this.wpzp = wpzp;
    }

    public String getKyxx() {
        return kyxx;
    }

    public void setKyxx(String kyxx) {
        this.kyxx = kyxx;
    }

    public Integer getDyId() {
        return dyId;
    }

    public void setDyId(Integer dyId) {
        this.dyId = dyId;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
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
        return "TblPhone{" +
        "id=" + id +
        ", brand=" + brand +
        ", xh=" + xh +
        ", ch=" + ch +
        ", color=" + color +
        ", jz=" + jz +
        ", sgsj=" + sgsj +
        ", wpzp=" + wpzp +
        ", kyxx=" + kyxx +
        ", dyId=" + dyId +
        ", lrsj=" + lrsj +
        ", creator=" + creator +
        ", modifier=" + modifier +
        ", createDate=" + createDate +
        ", modifyDate=" + modifyDate +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
