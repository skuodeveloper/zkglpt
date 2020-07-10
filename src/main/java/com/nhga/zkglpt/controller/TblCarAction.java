package com.nhga.zkglpt.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.nhga.zkglpt.util.ImageUtils;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblCarVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.nhga.zkglpt.controller.Constant.IMG_HEAD_URL;

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
            if (StringUtils.isNotEmpty(tblCar.getWpzp())) {
                JSONArray arrays = JSON.parseArray(tblCar.getWpzp());
                JSONArray newArrays = new JSONArray();
                arrays.forEach(o -> {
                    String imgPath = o.toString();
                    String base64 = ImageUtils.getBase64ByImgUrl("http://60.190.149.52:8096" + imgPath);
                    base64 = "data:image/" + imgPath.substring(imgPath.lastIndexOf(".") + 1) + ";base64," + base64;
                    newArrays.add(base64);
                });
                String jsonStr = JSON.toJSONString(newArrays);
                jsonStr = tblDyMapper.encryptAes(jsonStr);
                tblCar.setWpzpBase64(jsonStr);
            }

            tblCar.setCarNo(tblDyMapper.encryptAes(tblCar.getCarNo()));
            tblCar.setFdjh(tblDyMapper.encryptAes(tblCar.getFdjh()));
            tblCar.setCjh(tblDyMapper.encryptAes(tblCar.getCjh()));
            tblCar.setWpzp(tblDyMapper.encryptAes(tblCar.getWpzp()));
            tblCarMapper.insert(tblCar);

            TblCsr tblCsr = carRequest.getTblCsr();
            tblCsr.setParentId(tblCar.getId());
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
                jsonStr = tblDyMapper.encryptAes(jsonStr);
                tblCsr.setRyzpBase64(jsonStr);
            }

            tblCsr.setXm(tblDyMapper.encryptAes(tblCsr.getXm()));
            tblCsr.setSfzh(tblDyMapper.encryptAes(tblCsr.getSfzh()));
            tblCsr.setLxdh(tblDyMapper.encryptAes(tblCsr.getLxdh()));
            tblCsr.setZz(tblDyMapper.encryptAes(tblCsr.getZz()));
            tblCsr.setRyzp(tblDyMapper.encryptAes(tblCsr.getRyzp()));
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
            tblLog.setContent(tblDyMapper.encryptAes("新增车辆"));
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
            tblCarVos.forEach(o -> {
                o.getTblCsr().setXm(tblDyMapper.decryptAes(o.getTblCsr().getXm()));
            });

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
            List<TblCar> tblCars = tblCarMapper.selectList(new QueryWrapper<TblCar>().eq("id", id));
            List<TblCarVo> tblCarVos = super.convertToVoAndBindRelations(tblCars, TblCarVo.class);

            String carNo = tblDyMapper.decryptAes(tblCarVos.get(0).getCarNo());
            tblCarVos.get(0).setCarNo(carNo);

            String fdjh = tblDyMapper.decryptAes(tblCarVos.get(0).getFdjh());
            tblCarVos.get(0).setFdjh(fdjh);

            String cjh = tblDyMapper.decryptAes(tblCarVos.get(0).getCjh());
            tblCarVos.get(0).setCjh(cjh);

            String wpzp = tblDyMapper.decryptAes(tblCarVos.get(0).getWpzp());
            tblCarVos.get(0).setWpzp(wpzp);
            String wpzpBase64 = tblDyMapper.decryptAes(tblCarVos.get(0).getWpzpBase64());
            tblCarVos.get(0).setWpzpBase64(wpzpBase64);

            String xm = tblDyMapper.decryptAes(tblCarVos.get(0).getTblCsr().getXm());
            tblCarVos.get(0).getTblCsr().setXm(xm);
            String sfzh = tblDyMapper.decryptAes(tblCarVos.get(0).getTblCsr().getSfzh());
            tblCarVos.get(0).getTblCsr().setSfzh(sfzh);
            String lxdh = tblDyMapper.decryptAes(tblCarVos.get(0).getTblCsr().getLxdh());
            tblCarVos.get(0).getTblCsr().setLxdh(lxdh);
            String zz = tblDyMapper.decryptAes(tblCarVos.get(0).getTblCsr().getZz());
            tblCarVos.get(0).getTblCsr().setZz(zz);
            String ryzp = tblDyMapper.decryptAes(tblCarVos.get(0).getTblCsr().getRyzp());
            tblCarVos.get(0).getTblCsr().setRyzp(ryzp);
            String ryzpBase64 = tblDyMapper.decryptAes(tblCarVos.get(0).getTblCsr().getRyzpBase64());
            tblCarVos.get(0).getTblCsr().setRyzpBase64(ryzpBase64);
            return new JsonResult(Status.OK, tblCarVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

