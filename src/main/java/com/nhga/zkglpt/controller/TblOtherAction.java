package com.nhga.zkglpt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.nhga.zkglpt.mapper.TblOtherMapper;
import com.nhga.zkglpt.mapper.TblCsrMapper;
import com.nhga.zkglpt.mapper.TblDyMapper;
import com.nhga.zkglpt.mapper.TblLogMapper;
import com.nhga.zkglpt.model.*;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblOtherVo;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 其他信息表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/tblOther")
public class TblOtherAction extends BaseCrudRestController {
    @Resource
    private TblOtherMapper tblOtherMapper;
    @Resource
    private TblCsrMapper tblCsrMapper;
    @Resource
    private TblDyMapper tblDyMapper;
    @Resource
    private TblLogMapper tblLogMapper;

    @CrossOrigin
    @RequestMapping("/add")
    public BaseEntity add(HttpServletRequest request, @RequestBody OtherRequest otherRequest) {
        BaseEntity baseEntity = new BaseEntity<>();
        try {
            TblOther tblOther = otherRequest.getTblOther();
            tblOther.setLrsj(new Date());
            tblOther.setCreateDate(new Date());
            tblOther.setIsDeleted("N");
            tblOtherMapper.insert(tblOther);

            TblCsr tblCsr = otherRequest.getTblCsr();
            tblCsr.setParentId(tblOther.getId());
            tblCsrMapper.insert(tblCsr);

            //保存操作记录
            TblDy tblDy = tblDyMapper.selectById(tblOther.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent("新增其他物品");
            tblLog.setUrl("/tblOther/add");
            tblLog.setStatus("成功");
            tblLogMapper.insert(tblLog);

            baseEntity.setData(tblOther.getId());
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @RequestMapping("/get")
    public BaseEntityList<TblOther> get(@RequestParam int current, @RequestParam int size, @RequestParam int dyid) {
        BaseEntityList baseEntity = new BaseEntityList<>();
        try {
            Page<TblOther> page = tblOtherMapper.selectPage(
                    new Page<>(current, size),
                    new QueryWrapper<TblOther>().eq("dy_id", dyid).orderByDesc("lrsj"));

            List<TblOtherVo> tblOtherVos = super.convertToVoAndBindRelations(page.getRecords(), TblOtherVo.class);

            baseEntity.setCount(page.getTotal());
            baseEntity.setData(tblOtherVos);
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @RequestMapping("/getDetail")
    public JsonResult get(@RequestParam int id) {
        try {
            List<TblOther> tblOthers = tblOtherMapper.selectList(new QueryWrapper<TblOther>().eq("id", id));
            List<TblOtherVo> tblOtherVos = super.convertToVoAndBindRelations(tblOthers, TblOtherVo.class);
            return new JsonResult(Status.OK, tblOtherVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

