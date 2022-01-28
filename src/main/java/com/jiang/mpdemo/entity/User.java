package com.jiang.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jiang
 * @create 2022-01-28-4:42 下午
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @TableId(type = IdType.ID_WORKER) //mp自带策略，会生成19位数，数字类型使用这种策略，比如long
//    @TableId(type=IdType.ID_WORKER_STR) //mp自带策略，会生成19位数，字符串类型使用这种策略
    private Long id;
    private String name;
    private Integer age;
    private String email;


    @TableField(fill= FieldFill.INSERT)
    private Date createTime;

    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill= FieldFill.INSERT)
    private Integer version;

    @TableLogic
    private Integer deleted;
}
