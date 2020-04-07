package com.nhga.zkglpt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.nhga.zkglpt.mapper.TblComputerMapper;
import com.nhga.zkglpt.mapper.TblCsrMapper;
import com.nhga.zkglpt.mapper.TblDyMapper;
import com.nhga.zkglpt.mapper.TblLogMapper;
import com.nhga.zkglpt.model.*;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblComputerVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 二手电脑表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblComputer")
public class TblComputerAction extends BaseCrudRestController {
    @Resource
    private TblComputerMapper tblComputerMapper;
    @Resource
    private TblCsrMapper tblCsrMapper;
    @Resource
    private TblDyMapper tblDyMapper;
    @Resource
    private TblLogMapper tblLogMapper;

    @CrossOrigin
    @RequestMapping("/add")
    public BaseEntity add(HttpServletRequest request, @RequestBody ComputerRequest computerRequest) {
        BaseEntity baseEntity = new BaseEntity<>();
        try {
            TblComputer tblComputer = computerRequest.getTblComputer();
            tblComputer.setLrsj(new Date());
            tblComputer.setCreateDate(new Date());
            tblComputer.setIsDeleted("N");
            tblComputerMapper.insert(tblComputer);

            TblCsr tblCsr = computerRequest.getTblCsr();
            tblCsr.setParentId(tblComputer.getId());
            tblCsrMapper.insert(tblCsr);

            //保存操作记录
            TblDy tblDy = tblDyMapper.selectById(tblComputer.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent("新增电脑");
            tblLog.setUrl("/tblComputer/add");
            tblLog.setStatus("成功");
            tblLogMapper.insert(tblLog);

            baseEntity.setData(tblComputer.getId());
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @RequestMapping("/get")
    public BaseEntityList<TblComputer> get(@RequestParam int current, @RequestParam int size, @RequestParam int dyid) {
        BaseEntityList baseEntity = new BaseEntityList<>();
        try {
            Page<TblComputer> page = tblComputerMapper.selectPage(
                    new Page<>(current, size),
                    new QueryWrapper<TblComputer>().eq("dy_id", dyid).orderByDesc("lrsj"));

            List<TblComputerVo> tblComputerVos = super.convertToVoAndBindRelations(page.getRecords(), TblComputerVo.class);

            baseEntity.setCount(page.getTotal());
            baseEntity.setData(tblComputerVos);
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
            List<TblComputer> tblComputers = tblComputerMapper.selectList(new QueryWrapper<TblComputer>().eq("id", id));
            List<TblComputerVo> tblComputerVos = super.convertToVoAndBindRelations(tblComputers, TblComputerVo.class);
            return new JsonResult(Status.OK, tblComputerVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}