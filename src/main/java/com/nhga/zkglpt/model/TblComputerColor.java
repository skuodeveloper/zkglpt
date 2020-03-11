package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 电脑颜色表
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@TableName("tbl_color")
public class TblComputerColor implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 电脑颜色名称
     */
    @TableField("colorName")
    private String colorName;

    /**
     * 是否逻辑删除
     */
    private String isDeleted;


    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TblComputerColor{" +
        "colorName=" + colorName +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
