package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 电脑品牌表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblComputerBrand implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 电脑品牌名称
     */
    @TableField("brandName")
    private String brandName;

    /**
     * 是否逻辑删除
     */
    private String isDeleted;


    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TblComputerBrand{" +
        "brandName=" + brandName +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
