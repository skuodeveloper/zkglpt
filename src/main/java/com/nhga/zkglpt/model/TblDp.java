package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblDp implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 店铺编号
     */
    private String bh;

    /**
     * 店铺名称
     */
    private String mc;

    /**
     * 部门ID
     */
    private Integer departId;

    /**
     * 店铺地址
     */
    private String dz;

    /**
     * 店主姓名
     */
    private String dzxm;

    /**
     * 店主身份证号
     */
    private String dzsfzh;

    /**
     * 开业时间
     */
    private String kysj;

    /**
     * 经营范围
     */
    private String jyfw;

    /**
     * 店铺状态，0表示未审核,1表示已审核,2表示已注销
     */
    private String dpzt;

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
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime modifyDate;

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

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getDzxm() {
        return dzxm;
    }

    public void setDzxm(String dzxm) {
        this.dzxm = dzxm;
    }

    public String getDzsfzh() {
        return dzsfzh;
    }

    public void setDzsfzh(String dzsfzh) {
        this.dzsfzh = dzsfzh;
    }

    public String getKysj() {
        return kysj;
    }

    public void setKysj(String kysj) {
        this.kysj = kysj;
    }

    public String getJyfw() {
        return jyfw;
    }

    public void setJyfw(String jyfw) {
        this.jyfw = jyfw;
    }

    public String getDpzt() {
        return dpzt;
    }

    public void setDpzt(String dpzt) {
        this.dpzt = dpzt;
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
        return "TblDp{" +
        "id=" + id +
        ", bh=" + bh +
        ", mc=" + mc +
        ", departId=" + departId +
        ", dz=" + dz +
        ", dzxm=" + dzxm +
        ", dzsfzh=" + dzsfzh +
        ", kysj=" + kysj +
        ", jyfw=" + jyfw +
        ", dpzt=" + dpzt +
        ", creator=" + creator +
        ", modifier=" + modifier +
        ", createDate=" + createDate +
        ", modifyDate=" + modifyDate +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
