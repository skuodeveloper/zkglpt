package com.nhga.zkglpt.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.nhga.zkglpt.mapper.*;
import com.nhga.zkglpt.model.*;
import com.nhga.zkglpt.util.ImageUtils;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblComputerVo;
import com.nhga.zkglpt.vo.TblPhoneVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.nhga.zkglpt.controller.Constant.IMG_HEAD_URL;

/**
 * <p>
 * 二手手机表 前端控制器
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/tblPhone")
public class TblPhoneAction extends BaseCrudRestController {
    @Resource
    private TblPhoneMapper tblPhoneMapper;
    @Resource
    private TblCsrMapper tblCsrMapper;
    @Resource
    private TblDyMapper tblDyMapper;
    @Resource
    private TblLogMapper tblLogMapper;

    @CrossOrigin
    @RequestMapping("/add")
    public BaseEntity add(HttpServletRequest request, @RequestBody PhoneRequest phoneRequest) {
        BaseEntity baseEntity = new BaseEntity<>();
        try {
            TblPhone tblPhone = phoneRequest.getTblPhone();
            tblPhone.setLrsj(new Date());
            tblPhone.setCreateDate(new Date());
            tblPhone.setIsDeleted("N");
            if (StringUtils.isNotEmpty(tblPhone.getWpzp())) {
                JSONArray arrays = JSON.parseArray(tblPhone.getWpzp());
                JSONArray newArrays = new JSONArray();
                arrays.forEach(o -> {
                    String imgPath = o.toString();
                    String base64 = ImageUtils.getBase64ByImgUrl("http://60.190.149.52:8096" + imgPath);
                    base64 = "data:image/" + imgPath.substring(imgPath.lastIndexOf(".") + 1) + ";base64," + base64;
                    newArrays.add(base64);
                });
                String jsonStr = JSON.toJSONString(newArrays);
                tblPhone.setWpzpBase64(jsonStr);
            }
            tblPhoneMapper.insert(tblPhone);

            TblCsr tblCsr = phoneRequest.getTblCsr();
            tblCsr.setParentId(tblPhone.getId());
            if (StringUtils.isNotEmpty(tblCsr.getRyzp())) {
                JSONArray arrays = JSON.parseArray(tblCsr.getRyzp());
                JSONArray newArrays = new JSONArray();
                arrays.forEach(o -> {
                    String imgPath = o.toString();
                    String base64 = ImageUtils.getBase64ByImgUrl(IMG_HEAD_URL + imgPath);
                    base64 = "data:image/" + imgPath.substring(imgPath.lastIndexOf(".") + 1) + ";base64," + base64;
                    newArrays.add(base64);
                });
                String jsonStr = JSON.toJSONString(newArrays);
                tblCsr.setRyzpBase64(jsonStr);
            }
            tblCsrMapper.insert(tblCsr);

            //保存操作记录
            TblDy tblDy = tblDyMapper.selectById(tblPhone.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent("新增手机");
            tblLog.setUrl("/tblPhone/add");
            tblLog.setStatus("成功");
            tblLogMapper.insert(tblLog);

            baseEntity.setData(tblPhone.getId());
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            return baseEntity;
        }
    }

    @CrossOrigin
    @RequestMapping("/get")
    public BaseEntityList<TblPhone> get(@RequestParam int current, @RequestParam int size, @RequestParam int dyid) {
        BaseEntityList baseEntity = new BaseEntityList<>();
        try {
            Page<TblPhone> page = tblPhoneMapper.selectPage(
                    new Page<>(current, size),
                    new QueryWrapper<TblPhone>().eq("dy_id", dyid).orderByDesc("lrsj"));

            List<TblPhoneVo> tblPhoneVos = super.convertToVoAndBindRelations(page.getRecords(), TblPhoneVo.class);

            baseEntity.setCount(page.getTotal());
            baseEntity.setData(tblPhoneVos);
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
            List<TblPhone> tblComputers = tblPhoneMapper.selectList(new QueryWrapper<TblPhone>().eq("id", id));
            List<TblPhoneVo> tblComputerVos = super.convertToVoAndBindRelations(tblComputers, TblPhoneVo.class);
            return new JsonResult(Status.OK, tblComputerVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

