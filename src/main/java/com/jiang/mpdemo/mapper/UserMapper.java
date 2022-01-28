package com.jiang.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.mpdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiang
 * @create 2022-01-28-4:44 下午
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
