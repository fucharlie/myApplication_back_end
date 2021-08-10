/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import io.renren.modules.app.dao.UserDao;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	@Autowired
	UserDao userDao;

	/**
	 * 根据id返回用户信息
	 * @param id
	 * @return
	 */
	@Override
	public UserEntity findUserByID(int id) {
		UserEntity userEntity= userDao.getUserById(id);
		return userEntity;
	}

	/**
	 * 更新用户信息
	 * @param userEntity
	 */
	@Override
	public void updateUser(UserEntity userEntity) {
		userDao.updateUser(userEntity);
	}
}
