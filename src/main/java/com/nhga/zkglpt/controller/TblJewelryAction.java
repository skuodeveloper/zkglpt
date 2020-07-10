package com.nhga.zkglpt.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.nhga.zkglpt.util.ImageUtils;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblComputerVo;
import com.nhga.zkglpt.vo.TblJewelryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.nhga.zkglpt.controller.Constant.IMG_HEAD_URL;

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
            if (StringUtils.isNotEmpty(tblJewelry.getWpzp())) {
                JSONArray arrays = JSON.parseArray(tblJewelry.getWpzp());
                JSONArray newArrays = new JSONArray();
                arrays.forEach(o -> {
                    String imgPath = o.toString();
                    String base64 = ImageUtils.getBase64ByImgUrl("http://60.190.149.52:8096" + imgPath);
                    base64 = "data:image/" + imgPath.substring(imgPath.lastIndexOf(".") + 1) + ";base64," + base64;
                    newArrays.add(base64);
                });
                String jsonStr = JSON.toJSONString(newArrays);
                jsonStr = tblDyMapper.encryptAes(jsonStr);
                tblJewelry.setWpzpBase64(jsonStr);
            }

            tblJewelry.setWpzp(tblDyMapper.encryptAes(tblJewelry.getWpzp()));
            tblJewelryMapper.insert(tblJewelry);

            //保存出售人记录
            TblCsr tblCsr = jewelryRequest.getTblCsr();
            tblCsr.setParentId(tblJewelry.getId());
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
            TblDy tblDy = tblDyMapper.selectById(tblJewelry.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent(tblDyMapper.encryptAes("新增金饰"));
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
            List<TblJewelry> tblJewelries = tblJewelryMapper.selectList(new QueryWrapper<TblJewelry>().eq("id", id));
            List<TblJewelryVo> tblJewelryVos = super.convertToVoAndBindRelations(tblJewelries, TblJewelryVo.class);

            String wpzp = tblDyMapper.decryptAes(tblJewelryVos.get(0).getWpzp());
            tblJewelryVos.get(0).setWpzp(wpzp);
            String wpzpBase64 = tblDyMapper.decryptAes(tblJewelryVos.get(0).getWpzpBase64());
            tblJewelryVos.get(0).setWpzpBase64(wpzpBase64);

            String xm = tblDyMapper.decryptAes(tblJewelryVos.get(0).getTblCsr().getXm());
            tblJewelryVos.get(0).getTblCsr().setXm(xm);
            String sfzh = tblDyMapper.decryptAes(tblJewelryVos.get(0).getTblCsr().getSfzh());
            tblJewelryVos.get(0).getTblCsr().setSfzh(sfzh);
            String lxdh = tblDyMapper.decryptAes(tblJewelryVos.get(0).getTblCsr().getLxdh());
            tblJewelryVos.get(0).getTblCsr().setLxdh(lxdh);
            String zz = tblDyMapper.decryptAes(tblJewelryVos.get(0).getTblCsr().getZz());
            tblJewelryVos.get(0).getTblCsr().setZz(zz);
            String ryzp = tblDyMapper.decryptAes(tblJewelryVos.get(0).getTblCsr().getRyzp());
            tblJewelryVos.get(0).getTblCsr().setRyzp(ryzp);
            String ryzpBase64 = tblDyMapper.decryptAes(tblJewelryVos.get(0).getTblCsr().getRyzpBase64());
            tblJewelryVos.get(0).getTblCsr().setRyzpBase64(ryzpBase64);
            return new JsonResult(Status.OK, tblJewelryVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

