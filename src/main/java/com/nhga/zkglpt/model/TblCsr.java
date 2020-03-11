package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 出售人
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblCsr implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 性别
     */
    private String xb;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 街道乡镇
     */
    private String jdxz;

    /**
     * 住址
     */
    private String zz;

    /**
     * 其他描述
     */
    private String qtms;

    /**
     * 人员照片
     */
    private String ryzp;

    /**
     * 所属分类(电脑,手机,金饰,车辆,其他)
     */
    private String type;

    /**
     * 物品ID
     */
    private Integer parentId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getJdxz() {
        return jdxz;
    }

    public void setJdxz(String jdxz) {
        this.jdxz = jdxz;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getQtms() {
        return qtms;
    }

    public void setQtms(String qtms) {
        this.qtms = qtms;
    }

    public String getRyzp() {
        return ryzp;
    }

    public void setRyzp(String ryzp) {
        this.ryzp = ryzp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "TblCsr{" +
        "id=" + id +
        ", xm=" + xm +
        ", xb=" + xb +
        ", sfzh=" + sfzh +
        ", lxdh=" + lxdh +
        ", jdxz=" + jdxz +
        ", zz=" + zz +
        ", qtms=" + qtms +
        ", ryzp=" + ryzp +
        ", type=" + type +
        ", parentId=" + parentId +
        "}";
    }
}
