package com.nhga.zkglpt.service.serviceImpl;

import com.nhga.zkglpt.model.TblUser;
import com.nhga.zkglpt.mapper.TblUserMapper;
import com.nhga.zkglpt.service.TblUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Alpha
 * @since 2020-02-17
 */
@Service
public class TblUserServiceImpl extends ServiceImpl<TblUserMapper, TblUser> implements TblUserService {

}
