package com.nhga.zkglpt.mapper;

import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.diboot.core.mapper.BaseCrudMapper;
import com.nhga.zkglpt.model.TblComputer;

/**
 * <p>
 * 二手电脑表 Mapper 接口
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
public interface TblComputerMapper extends BaseCrudMapper<TblComputer> {
    @Override
    int insert(TblComputer tblComputer);
}
