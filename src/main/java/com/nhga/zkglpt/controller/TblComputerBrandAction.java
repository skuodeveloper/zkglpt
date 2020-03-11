package com.nhga.zkglpt.controller;


import com.nhga.zkglpt.mapper.TblComputerBrandMapper;
import com.nhga.zkglpt.model.TblComputerBrand;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 电脑品牌表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblComputerBrand")
public class TblComputerBrandAction {
    @Resource
    private TblComputerBrandMapper tblComputerBrandMapper;

    @CrossOrigin
    @RequestMapping("/get")
    public List<TblComputerBrand> get() {
        try {
            return tblComputerBrandMapper.selectList(null);
        } catch (Exception ex) {
            return null;
        }
    }
}

