package com.nhga.zkglpt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.nhga.zkglpt.mapper.TblCarMapper;
import com.nhga.zkglpt.mapper.TblCsrMapper;
import com.nhga.zkglpt.mapper.TblDyMapper;
import com.nhga.zkglpt.mapper.TblLogMapper;
import com.nhga.zkglpt.model.*;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblCarVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 车辆回收维修表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/tblCar")
public class TblCarAction extends BaseCrudRestController {
    @Resource
    private TblCarMapper tblCarMapper;
    @Resource
    private TblCsrMapper tblCsrMapper;
    @Resource
    private TblDyMapper tblDyMapper;
    @Resource
    private TblLogMapper tblLogMapper;
    
    @CrossOrigin
    @ApiOperation(value = "新增", notes = "新增二手车辆")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseEntity add(HttpServletRequest request, @RequestBody CarRequest carRequest) {
        BaseEntity baseEntity = new BaseEntity<>();
        try {
            TblCar tblCar = carRequest.getTblCar();
            tblCar.setCreateDate(new Date());
            tblCar.setLrsj(new Date());
            tblCar.setIsDeleted("N");
            tblCarMapper.insert(tblCar);

            TblCsr tblCsr = carRequest.getTblCsr();
            tblCsr.setParentId(tblCar.getId());
            tblCsrMapper.insert(tblCsr);

            //保存操作记录
            TblDy tblDy = tblDyMapper.selectById(tblCar.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent("新增车辆");
            tblLog.setUrl("/tblCar/add");
            tblLog.setStatus("成功");
            tblLogMapper.insert(tblLog);

            baseEntity.setData(tblCar.getId());
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @ApiOperation(value = "列表", notes = "获取车辆列表")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public BaseEntityList<TblCar> get(@RequestParam int current, @RequestParam int size, @RequestParam int dyid) {
        BaseEntityList baseEntity = new BaseEntityList<>();
        try {
            Page<TblCar> page = tblCarMapper.selectPage(
                    new Page<>(current, size),
                    new QueryWrapper<TblCar>().eq("dy_id", dyid).orderByDesc("lrsj"));

            List<TblCarVo> tblCarVos = super.convertToVoAndBindRelations(page.getRecords(), TblCarVo.class);
            tblCarVos.get(0).setIsDeleted("1");
            baseEntity.setCount(page.getTotal());
            baseEntity.setData(tblCarVos);
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @ApiOperation(value = "详情", notes = "获取车辆详情")
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public JsonResult get(@RequestParam int id) {
        try {
            List<TblCar> tblComputers = tblCarMapper.selectList(new QueryWrapper<TblCar>().eq("id", id));
            List<TblCarVo> tblComputerVos = super.convertToVoAndBindRelations(tblComputers, TblCarVo.class);
            return new JsonResult(Status.OK, tblComputerVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

