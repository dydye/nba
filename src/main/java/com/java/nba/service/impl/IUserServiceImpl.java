package com.java.nba.service.impl;

import com.java.nba.dao.IUserDao;
import com.java.nba.model.User;
import com.java.nba.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author daiyun
 * @date 2019-4-9
 */
@Service("userService")
public class IUserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
}
