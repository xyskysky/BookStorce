package com.jwn.bookstore.dao;

import java.util.Set;

import com.jwn.bookstore.domain.Trade;

public interface TradeDAO
{
	void insert(Trade trade);

	/**
	 * 根据Userid获取Trade的集合
	 * 
	 * @param userId
	 * @return
	 */
	Set<Trade> getTradeWithUserId(Integer userId);
}
