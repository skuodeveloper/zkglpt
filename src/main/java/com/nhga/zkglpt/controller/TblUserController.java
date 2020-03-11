package com.nhga.zkglpt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nhga.zkglpt.mapper.TblUserMapper;
import com.nhga.zkglpt.model.BaseEntity;
import com.nhga.zkglpt.model.TblUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblUser")
public class TblUserController {
    @Resource
    private TblUserMapper tblUserMapper;

    @CrossOrigin
    @RequestMapping("/login")
    public BaseEntity<TblUser> login(@RequestBody TblUser tblUser) {
        BaseEntity<TblUser> baseEntity = new BaseEntity<>();
        try {
            List<TblUser> tblUsers = tblUserMapper.selectList(
                    new QueryWrapper<TblUser>().eq("username", tblUser.getUsername()).eq("password", tblUser.getPassword()));
            if (tblUsers.size() == 0) {
                baseEntity.setCode(-1);
                baseEntity.setMessage("对不起，您输入的用户名或密码不正确！");
            } else {
                baseEntity.setCode(0);
                baseEntity.setData(tblUsers.get(0));
            }
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }
}

