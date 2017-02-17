package com.jwn.bookstore.service;

import com.jwn.bookstore.dao.AccountDAO;
import com.jwn.bookstore.dao.impl.AccountDAOImpl;
import com.jwn.bookstore.domain.Account;

public class AccountService
{
	private AccountDAO accountDAO = new AccountDAOImpl();

	public Account getAccount(int accountId)
	{
		return accountDAO.getAccountByAccountId(accountId);
	}
}
