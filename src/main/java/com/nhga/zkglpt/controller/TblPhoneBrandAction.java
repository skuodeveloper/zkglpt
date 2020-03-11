package com.nhga.zkglpt.controller;


import com.nhga.zkglpt.mapper.TblPhoneBrandMapper;
import com.nhga.zkglpt.model.TblPhoneBrand;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 手机品牌表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblPhoneBrand")
public class TblPhoneBrandAction {
    @Resource
    private TblPhoneBrandMapper tblPhoneBrandMapper;

    @CrossOrigin
    @RequestMapping("/get")
    public List<TblPhoneBrand> get() {
        try {
            return tblPhoneBrandMapper.selectList(null);
        } catch (Exception ex) {
            return null;
        }
    }
}

