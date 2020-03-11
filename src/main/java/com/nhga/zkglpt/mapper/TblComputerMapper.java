package com.nhga.zkglpt.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.nhga.zkglpt.model.TblComputer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

/**
 * <p>
 * 二手电脑表 Mapper 接口
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
public interface TblComputerMapper extends BaseCrudMapper<TblComputer> {
}
