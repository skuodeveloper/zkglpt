package com.nhga.zkglpt.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.nhga.zkglpt.util.ImageUtils;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblComputerVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.nhga.zkglpt.controller.Constant.IMG_HEAD_URL;

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
            if (StringUtils.isNotEmpty(tblComputer.getWpzp())) {
                JSONArray arrays = JSON.parseArray(tblComputer.getWpzp());
                JSONArray newArrays = new JSONArray();
                arrays.forEach(o -> {
                    String imgPath = o.toString();
                    String base64 = ImageUtils.getBase64ByImgUrl(IMG_HEAD_URL + imgPath);
                    base64 = "data:image/" + imgPath.substring(imgPath.lastIndexOf(".") + 1) + ";base64," + base64;
                    newArrays.add(base64);
                });
                String jsonStr = JSON.toJSONString(newArrays);
                jsonStr = tblDyMapper.encryptAes(jsonStr);
                tblComputer.setWpzpBase64(jsonStr);
            }

            String xlh = tblDyMapper.encryptAes(tblComputer.getXlh());
            tblComputer.setXlh(xlh);

            String wpzp = tblDyMapper.encryptAes(tblComputer.getWpzp());
            tblComputer.setWpzp(wpzp);

            tblComputerMapper.insert(tblComputer);

            TblCsr tblCsr = computerRequest.getTblCsr();
            tblCsr.setParentId(tblComputer.getId());
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
            TblDy tblDy = tblDyMapper.selectById(tblComputer.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            String content = tblDyMapper.encryptAes("新增电脑");
            tblLog.setContent(content);
            tblLog.setUrl("/tblComputer/add");
            tblLog.setStatus("成功");
            tblLogMapper.insert(tblLog);

            baseEntity.setData(tblComputer.getId());
            return baseEntity;
        } catch (Exception ex) {
            baseEntity.setCode(-1);
            baseEntity.setMessage(ex.getMessage());
            System.out.println(ex.getMessage());
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
            tblComputerVos.forEach(o -> {
                o.getTblCsr().setXm(tblDyMapper.decryptAes(o.getTblCsr().getXm()));
            });

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

            String xlh = tblDyMapper.decryptAes(tblComputerVos.get(0).getXlh());
            tblComputerVos.get(0).setXlh(xlh);
            String wpzp = tblDyMapper.decryptAes(tblComputerVos.get(0).getWpzp());
            tblComputerVos.get(0).setWpzp(wpzp);
            String wpzpBase64 = tblDyMapper.decryptAes(tblComputerVos.get(0).getWpzpBase64());
            tblComputerVos.get(0).setWpzpBase64(wpzpBase64);

            String xm = tblDyMapper.decryptAes(tblComputerVos.get(0).getTblCsr().getXm());
            tblComputerVos.get(0).getTblCsr().setXm(xm);
            String sfzh = tblDyMapper.decryptAes(tblComputerVos.get(0).getTblCsr().getSfzh());
            tblComputerVos.get(0).getTblCsr().setSfzh(sfzh);
            String lxdh = tblDyMapper.decryptAes(tblComputerVos.get(0).getTblCsr().getLxdh());
            tblComputerVos.get(0).getTblCsr().setLxdh(lxdh);
            String zz = tblDyMapper.decryptAes(tblComputerVos.get(0).getTblCsr().getZz());
            tblComputerVos.get(0).getTblCsr().setZz(zz);
            String ryzp = tblDyMapper.decryptAes(tblComputerVos.get(0).getTblCsr().getRyzp());
            tblComputerVos.get(0).getTblCsr().setRyzp(ryzp);
            String ryzpBase64 = tblDyMapper.decryptAes(tblComputerVos.get(0).getTblCsr().getRyzpBase64());
            tblComputerVos.get(0).getTblCsr().setRyzpBase64(ryzpBase64);

            return new JsonResult(Status.OK, tblComputerVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}