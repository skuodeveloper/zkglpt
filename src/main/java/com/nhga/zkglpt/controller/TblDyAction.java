package com.nhga.zkglpt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nhga.zkglpt.mapper.TblDyLginfoMapper;
import com.nhga.zkglpt.mapper.TblDyMapper;
import com.nhga.zkglpt.mapper.TblLogMapper;
import com.nhga.zkglpt.model.BaseEntity;
import com.nhga.zkglpt.model.TblDy;
import com.nhga.zkglpt.model.TblDyLginfo;
import com.nhga.zkglpt.model.TblLog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 店员表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblDy")
public class TblDyAction {
    @Resource
    private TblDyMapper tblDyMapper;

    @Resource
    private TblLogMapper tblLogMapper;

    @CrossOrigin
    @RequestMapping("/login")
    public BaseEntity<TblDy> login(@RequestBody TblDy tblUser) {
        BaseEntity<TblDy> baseEntity = new BaseEntity<>();
        try {
            List<TblDy> tblUsers = tblDyMapper.selectList(
                    new QueryWrapper<TblDy>()
                            .eq("phone", tblUser.getPhone())
                            .eq("pwd", tblUser.getPwd())
                            .eq("dyzt", "1"));
            if (tblUsers.size() == 0) {
                baseEntity.setCode(-1);
                baseEntity.setMessage("对不起，您输入的用户名或密码不正确！");
            } else {
                baseEntity.setCode(0);
                baseEntity.setData(tblUsers.get(0));

                //保存操作记录
                TblLog tblLog = new TblLog();
                tblLog.setDepartId(tblUsers.get(0).getDpId());
                tblLog.setSfzh(tblUsers.get(0).getSfzh());
                tblLog.setXm(tblUsers.get(0).getMc());
                tblLog.setPhone(tblUsers.get(0).getPhone());
                tblLog.setCzsj(new Date());
                tblLog.setIp(tblUser.getIp());
                tblLog.setContent("店员登录");
                tblLog.setUrl("/tblDy/login");
                tblLog.setStatus("成功");
                tblLogMapper.insert(tblLog);
            }
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @RequestMapping("/getImgUrl")
    public String getImgUrl(@RequestParam String base64) {
        return "https://desk-fd.zol-img.com.cn/t_s1600x900c5/g5/M00/02/00/ChMkJlbKw1eIdabyAASvPG-H6SwAALG1gFD3VQABK9U648.jpg";
    }
}

