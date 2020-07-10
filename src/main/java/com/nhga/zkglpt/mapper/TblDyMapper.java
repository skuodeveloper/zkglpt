package com.nhga.zkglpt.mapper;

import com.nhga.zkglpt.model.TblDy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 店员表 Mapper 接口
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
public interface TblDyMapper extends BaseMapper<TblDy> {
    List<TblDy> getDyList(TblDy tblDy);

    @Select("SELECT encryptAes(#{str},'95352362a9d342ae80043298989ff442') as value FROM DUAL")
    String encryptAes(@Param("str") String str);

    @Select("SELECT decryptAes(#{str},'95352362a9d342ae80043298989ff442') as value FROM DUAL")
    String decryptAes(@Param("str") String str);
}
