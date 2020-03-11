package com.nhga.zkglpt.controller;


import com.nhga.zkglpt.mapper.TblComputerColorMapper;
import com.nhga.zkglpt.model.TblComputerColor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 电脑颜色表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblComputerColor")
public class TblComputerColorAction {
    @Resource
    private TblComputerColorMapper tblComputerColorMapper;

    @CrossOrigin
    @RequestMapping("/get")
    public List<TblComputerColor> get() {
        try {
            return tblComputerColorMapper.selectList(null);
        } catch (Exception ex) {
            return null;
        }
    }
}

