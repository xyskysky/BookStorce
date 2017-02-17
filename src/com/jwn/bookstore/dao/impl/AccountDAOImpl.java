package com.jwn.bookstore.dao.impl;

import com.jwn.bookstore.dao.AccountDAO;
import com.jwn.bookstore.domain.Account;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO
{

	@Override
	public Account getAccountByAccountId(Integer accountId)
	{
		String sql = "select accountid,balance from account where accountid=?";

		return query(sql, accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount)
	{
		String sql = "update account set balance=balance-? where accountid=? ";
		update(sql, amount, accountId);
	}

}
