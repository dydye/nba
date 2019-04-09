package com.java.nba.dao;

import com.java.nba.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author daiyun
 * @date 2019-4-9
 */
@Repository
public interface IUserDao {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}
