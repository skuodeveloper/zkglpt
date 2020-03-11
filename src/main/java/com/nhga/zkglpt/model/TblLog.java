package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作人部门所属ID
     */
    private Integer departId;

    /**
     * 操作人身份证
     */
    private String sfzh;

    /**
     * 操作人姓名
     */
    private String xm;

    /**
     * 操作人手机号
     */
    private String phone;

    /**
     * 操作时间
     */
    private Date czsj;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 操作内容
     */
    private String content;

    /**
     * URL地址
     */
    private String url;

    /**
     * 执行状态
     */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblLog{" +
        "id=" + id +
        ", departId=" + departId +
        ", sfzh=" + sfzh +
        ", xm=" + xm +
        ", phone=" + phone +
        ", czsj=" + czsj +
        ", ip=" + ip +
        ", content=" + content +
        ", url=" + url +
        ", status=" + status +
        "}";
    }
}
