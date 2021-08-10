/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


//通过BaseMapper继承对数据库所有的操作

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    public UserEntity getUserById(int user_id);

    public void updateUser(UserEntity userEntity);




}
