package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
public class TblDepart implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 部门
     */
    @TableField("departName")
    private String departName;

    /**
     * 是否逻辑删除
     */
    private String isDeleted;


    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TblDepart{" +
        "departName=" + departName +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
