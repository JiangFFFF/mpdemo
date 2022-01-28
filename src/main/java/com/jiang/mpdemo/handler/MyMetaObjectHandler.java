package com.jiang.mpdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author jiang
 * @create 2022-01-28-5:57 下午
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 使用mp实现添加操作，这个方法会执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);

        this.setFieldValByName("version",1,metaObject);
    }

    /**
     * 使用mp实现修改操作，这个方法会执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
