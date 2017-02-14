package com.jwn.bookstore.dao.impl;

import com.jwn.bookstore.dao.UserDAO;
import com.jwn.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO
{

	@Override
	public User getUser(String username)
	{
		String sql = "select userid, username, accountid " +
				"FROM userinfo WHERE username = ?";
		return query(sql, username);
	}

}
