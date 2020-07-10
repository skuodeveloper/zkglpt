package com.nhga.zkglpt.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.nhga.zkglpt.util.ImageUtils;
import com.nhga.zkglpt.util.IpUtil;
import com.nhga.zkglpt.vo.TblOtherVo;
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
            if (StringUtils.isNotEmpty(tblOther.getWpzp())) {
                JSONArray arrays = JSON.parseArray(tblOther.getWpzp());
                JSONArray newArrays = new JSONArray();
                arrays.forEach(o -> {
                    String imgPath = o.toString();
                    String base64 = ImageUtils.getBase64ByImgUrl("http://60.190.149.52:8096" + imgPath);
                    base64 = "data:image/" + imgPath.substring(imgPath.lastIndexOf(".") + 1) + ";base64," + base64;
                    newArrays.add(base64);
                });
                String jsonStr = JSON.toJSONString(newArrays);
                jsonStr = tblDyMapper.encryptAes(jsonStr);
                tblOther.setWpzpBase64(jsonStr);
            }

            tblOther.setWpzp(tblDyMapper.encryptAes(tblOther.getWpzp()));
            tblOtherMapper.insert(tblOther);

            TblCsr tblCsr = otherRequest.getTblCsr();
            tblCsr.setParentId(tblOther.getId());
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
            TblDy tblDy = tblDyMapper.selectById(tblOther.getDyId());
            TblLog tblLog = new TblLog();
            tblLog.setDepartId(tblDy.getDpId());
            tblLog.setSfzh(tblDy.getSfzh());
            tblLog.setXm(tblDy.getMc());
            tblLog.setPhone(tblDy.getPhone());
            tblLog.setCzsj(new Date());
            tblLog.setIp(IpUtil.getIpAddr(request));
            tblLog.setContent(tblDyMapper.encryptAes("新增其他物品"));
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
            tblOtherVos.forEach(o -> {
                o.getTblCsr().setXm(tblDyMapper.decryptAes(o.getTblCsr().getXm()));
            });

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

            String wpzp = tblDyMapper.decryptAes(tblOtherVos.get(0).getWpzp());
            tblOtherVos.get(0).setWpzp(wpzp);
            String wpzpBase64 = tblDyMapper.decryptAes(tblOtherVos.get(0).getWpzpBase64());
            tblOtherVos.get(0).setWpzpBase64(wpzpBase64);

            String xm = tblDyMapper.decryptAes(tblOtherVos.get(0).getTblCsr().getXm());
            tblOtherVos.get(0).getTblCsr().setXm(xm);
            String sfzh = tblDyMapper.decryptAes(tblOtherVos.get(0).getTblCsr().getSfzh());
            tblOtherVos.get(0).getTblCsr().setSfzh(sfzh);
            String lxdh = tblDyMapper.decryptAes(tblOtherVos.get(0).getTblCsr().getLxdh());
            tblOtherVos.get(0).getTblCsr().setLxdh(lxdh);
            String zz = tblDyMapper.decryptAes(tblOtherVos.get(0).getTblCsr().getZz());
            tblOtherVos.get(0).getTblCsr().setZz(zz);
            String ryzp = tblDyMapper.decryptAes(tblOtherVos.get(0).getTblCsr().getRyzp());
            tblOtherVos.get(0).getTblCsr().setRyzp(ryzp);
            String ryzpBase64 = tblDyMapper.decryptAes(tblOtherVos.get(0).getTblCsr().getRyzpBase64());
            tblOtherVos.get(0).getTblCsr().setRyzpBase64(ryzpBase64);

            return new JsonResult(Status.OK, tblOtherVos.get(0));
        } catch (Exception ex) {
            return new JsonResult(Status.FAIL_EXCEPTION, ex.getMessage());
        }
    }
}

