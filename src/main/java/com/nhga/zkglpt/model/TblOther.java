package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 其他信息表
 * </p>
 *
 * @author Alpha
 * @since 2020-05-06
 */
public class TblOther implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物品名称
     */
    private String wpmc;

    /**
     * 颜色
     */
    private String color;

    /**
     * 型号
     */
    private String xh;

    /**
     * 特征号
     */
    private String tzh;

    /**
     * 价值
     */
    private String jz;

    /**
     * 特征描述
     */
    private String tzms;

    /**
     * 回收时间
     */
    private String hssj;

    /**
     * 备注
     */
    private String bz;

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

    /**
     * 物品照片base64
     */
    private String wpzpBase64;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWpmc() {
        return wpmc;
    }

    public void setWpmc(String wpmc) {
        this.wpmc = wpmc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getTzh() {
        return tzh;
    }

    public void setTzh(String tzh) {
        this.tzh = tzh;
    }

    public String getJz() {
        return jz;
    }

    public void setJz(String jz) {
        this.jz = jz;
    }

    public String getTzms() {
        return tzms;
    }

    public void setTzms(String tzms) {
        this.tzms = tzms;
    }

    public String getHssj() {
        return hssj;
    }

    public void setHssj(String hssj) {
        this.hssj = hssj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public String getWpzpBase64() {
        return wpzpBase64;
    }

    public void setWpzpBase64(String wpzpBase64) {
        this.wpzpBase64 = wpzpBase64;
    }

    @Override
    public String toString() {
        return "TblOther{" +
        "id=" + id +
        ", wpmc=" + wpmc +
        ", color=" + color +
        ", xh=" + xh +
        ", tzh=" + tzh +
        ", jz=" + jz +
        ", tzms=" + tzms +
        ", hssj=" + hssj +
        ", bz=" + bz +
        ", wpzp=" + wpzp +
        ", kyxx=" + kyxx +
        ", dyId=" + dyId +
        ", lrsj=" + lrsj +
        ", creator=" + creator +
        ", modifier=" + modifier +
        ", createDate=" + createDate +
        ", modifyDate=" + modifyDate +
        ", isDeleted=" + isDeleted +
        ", wpzpBase64=" + wpzpBase64 +
        "}";
    }
}
