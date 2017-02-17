package com.jwn.bookstore.dao;

import com.jwn.bookstore.domain.Account;

public interface AccountDAO
{
	Account getAccountByAccountId(Integer accountId);
	
	/**
	 * ÐÞ¸ÄÕËºÅ½ð¶î
	 * @param accountId
	 * @param amount
	 */
	void updateBalance(Integer accountId,float amount);
}
