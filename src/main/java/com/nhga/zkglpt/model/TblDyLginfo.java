package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 店员登陆表
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
public class TblDyLginfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer dyId;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 登陆时间
     */
    private Date loginTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDyId() {
        return dyId;
    }

    public void setDyId(Integer dyId) {
        this.dyId = dyId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "TblDyLginfo{" +
        "id=" + id +
        ", dyId=" + dyId +
        ", ip=" + ip +
        ", loginTime=" + loginTime +
        "}";
    }
}
