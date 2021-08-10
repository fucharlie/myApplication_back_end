/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private int user_id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 账户金额
	 */
	private Integer account_money ;
	/**
	 * 保证金
	 */
	private Integer bond;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 充值金额
	 */
	private int money;

}
