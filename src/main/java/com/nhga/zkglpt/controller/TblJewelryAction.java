package com.nhga.zkglpt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.nhga.zkglpt.mapper.TblCsrMapper;
import com.nhga.zkglpt.mapper.TblDyMapper;
import com.nhga.zkglpt.mapper.TblJewelryMapper;
import com.nhga.zkglpt.mapper.TblLogMapper;
import com.nhga.zkglpt.model.*;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblComputerVo;
import com.nhga.zkglpt.vo.TblJewelryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 二手金饰表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblJewelry")
public class TblJewelryAction extends BaseCrudRestController {
    @Resource
    private TblJewelryMapper tblJewelryMapper;
    @Resource
    private TblCsrMapper tblCsrMapper;
    @Resource
    private TblLogMapper tblLogMapper;
    @Resource
    private TblDyMapper tblDyMapper;

    @CrossOrigin
    @RequestMapping("/add")
    public BaseEntity add(HttpServletRequest request, @RequestBody JewelryRequest jewelryRequest) {
        BaseEntity baseEntity = new BaseEntity<>();
        try {
            //保存金饰记录
            TblJewelry tblJewelry = jewelryRequest.getTblJewelry();
            tblJewelry.setLrsj(new Date());
            tblJewelry.setCreateDate(new Date());
            tblJewelry.setIsDeleted("N");
            tblJewelryMapper.insert(tblJewelry);

            //保存出售人记录
            TblCsr tblCsr = jewelryRequest.getTblCsr();
            tblCsr.setParentId(tblJewelry.getId());
            tblCsrMapper.insert(tblCsr);

            //保存操作记录
            TblDy tblDy = tblDyMapper.selectById(tblJewelry.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent("新增金饰");
            tblLog.setUrl("/tblJewelry/add");
            tblLog.setStatus("成功");
            tblLogMapper.insert(tblLog);

            baseEntity.setData(tblJewelry.getId());
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @RequestMapping("/get")
    public BaseEntityList<TblJewelry> get(@RequestParam int current, @RequestParam int size, @RequestParam int dyid) {
        BaseEntityList baseEntity = new BaseEntityList<>();
        try {
            Page<TblJewelry> page = tblJewelryMapper.selectPage(
                    new Page<>(current, size),
                    new QueryWrapper<TblJewelry>().eq("dy_id", dyid).orderByDesc("lrsj"));

            List<TblJewelryVo> tblComputerVos = super.convertToVoAndBindRelations(page.getRecords(), TblJewelryVo.class);

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
            List<TblJewelry> tblJewelries = tblJewelryMapper.selectList(new QueryWrapper<TblJewelry>().eq("id", id));
            List<TblJewelryVo> tblJewelryVos = super.convertToVoAndBindRelations(tblJewelries, TblJewelryVo.class);
            return new JsonResult(Status.OK, tblJewelryVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

