package com.nhga.zkglpt.hander;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
//@Component
public class MetaHandler implements MetaObjectHandler {
    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//        SysUserEntity userEntity = ShiroUtil.getUser();
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("createBy", userEntity.getLoginName(), metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("updateBy", userEntity.getLoginName(), metaObject);
    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
//        SysUserEntity userEntity = ShiroUtil.getUser();
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("updateBy", userEntity.getLoginName(), metaObject);
    }
}
