package com.jwn.bookstore.dao;

import java.util.Set;

import com.jwn.bookstore.domain.Trade;

public interface TradeDAO
{
	void insert(Trade trade);

	/**
	 * ����Userid��ȡTrade�ļ���
	 * 
	 * @param userId
	 * @return
	 */
	Set<Trade> getTradeWithUserId(Integer userId);
}
