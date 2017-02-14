package com.jwn.bookstore.dao;

import com.jwn.bookstore.domain.User;

public interface UserDAO
{
	User getUser(String username);
}
