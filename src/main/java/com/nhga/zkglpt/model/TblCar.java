package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 车辆回收维修表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TblCar implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车型
     */
    private String brand;

    /**
     * 0:维修,1:回收
     */
    private String type;

    /**
     * 0:机动车,1:非机动车
     */
    private String carType;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 特征描述
     */
    private String tzms;

    /**
     * 发动机号
     */
    private String fdjh;

    /**
     * 车架号
     */
    private String cjh;

    /**
     * 维修记录
     */
    private String wxjl;

    /**
     * 维修时间
     */
    private String wxsj;

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


//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getCarType() {
//        return carType;
//    }
//
//    public void setCarType(String carType) {
//        this.carType = carType;
//    }
//
//    public String getCarNo() {
//        return carNo;
//    }
//
//    public void setCarNo(String carNo) {
//        this.carNo = carNo;
//    }
//
//    public String getTzms() {
//        return tzms;
//    }
//
//    public void setTzms(String tzms) {
//        this.tzms = tzms;
//    }
//
//    public String getFdjh() {
//        return fdjh;
//    }
//
//    public void setFdjh(String fdjh) {
//        this.fdjh = fdjh;
//    }
//
//    public String getCjh() {
//        return cjh;
//    }
//
//    public void setCjh(String cjh) {
//        this.cjh = cjh;
//    }
//
//    public String getWxjl() {
//        return wxjl;
//    }
//
//    public void setWxjl(String wxjl) {
//        this.wxjl = wxjl;
//    }
//
//    public String getWxsj() {
//        return wxsj;
//    }
//
//    public void setWxsj(String wxsj) {
//        this.wxsj = wxsj;
//    }
//
//    public String getWpzp() {
//        return wpzp;
//    }
//
//    public void setWpzp(String wpzp) {
//        this.wpzp = wpzp;
//    }
//
//    public String getKyxx() {
//        return kyxx;
//    }
//
//    public void setKyxx(String kyxx) {
//        this.kyxx = kyxx;
//    }
//
//    public Integer getDyId() {
//        return dyId;
//    }
//
//    public void setDyId(Integer dyId) {
//        this.dyId = dyId;
//    }
//
//    public Date getLrsj() {
//        return lrsj;
//    }
//
//    public void setLrsj(Date lrsj) {
//        this.lrsj = lrsj;
//    }
//
//    public String getCreator() {
//        return creator;
//    }
//
//    public void setCreator(String creator) {
//        this.creator = creator;
//    }
//
//    public String getModifier() {
//        return modifier;
//    }
//
//    public void setModifier(String modifier) {
//        this.modifier = modifier;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getModifyDate() {
//        return modifyDate;
//    }
//
//    public void setModifyDate(Date modifyDate) {
//        this.modifyDate = modifyDate;
//    }
//
//    public String getIsDeleted() {
//        return isDeleted;
//    }
//
//    public void setIsDeleted(String isDeleted) {
//        this.isDeleted = isDeleted;
//    }

//    @Override
//    public String toString() {
//        return "TblCar{" +
//        "id=" + id +
//        ", brand=" + brand +
//        ", type=" + type +
//        ", carType=" + carType +
//        ", carNo=" + carNo +
//        ", tzms=" + tzms +
//        ", fdjh=" + fdjh +
//        ", cjh=" + cjh +
//        ", wxjl=" + wxjl +
//        ", wxsj=" + wxsj +
//        ", wpzp=" + wpzp +
//        ", kyxx=" + kyxx +
//        ", dyId=" + dyId +
//        ", lrsj=" + lrsj +
//        ", creator=" + creator +
//        ", modifier=" + modifier +
//        ", createDate=" + createDate +
//        ", modifyDate=" + modifyDate +
//        ", isDeleted=" + isDeleted +
//        "}";
//    }
}
