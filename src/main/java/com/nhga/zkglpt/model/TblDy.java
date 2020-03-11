package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 店员表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblDy implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    private String bh;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 用户名称
     */
    private String mc;

    /**
     * 登陆密码
     */
    private String pwd;

    /**
     * 性别，0表示男，1表示女
     */
    private String xb;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 关联店铺ID
     */
    private Integer dpId;

    /**
     * 人员现住址
     */
    private String ryxzz;

    /**
     * 店员状态，0表示未审核,1表示已审核,2表示已注销
     */
    private String dyzt;


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


    @TableField(exist = false)
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

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

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public String getRyxzz() {
        return ryxzz;
    }

    public void setRyxzz(String ryxzz) {
        this.ryxzz = ryxzz;
    }

    public String getDyzt() {
        return dyzt;
    }

    public void setDyzt(String dyzt) {
        this.dyzt = dyzt;
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
        return "TblDy{" +
        "id=" + id +
        ", bh=" + bh +
        ", sfzh=" + sfzh +
        ", mc=" + mc +
        ", pwd=" + pwd +
        ", xb=" + xb +
        ", phone=" + phone +
        ", dpId=" + dpId +
        ", ryxzz=" + ryxzz +
        ", dyzt=" + dyzt +
        ", creator=" + creator +
        ", modifier=" + modifier +
        ", createDate=" + createDate +
        ", modifyDate=" + modifyDate +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
