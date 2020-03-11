package com.nhga.zkglpt.model;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 手机品牌表
 * </p>
 *
 * @author Alpha
 * @since 2020-03-11
 */
public class TblPhoneBrand implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 手机品牌名称
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
        return "TblPhoneBrand{" +
        "brandName=" + brandName +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
