package com.jwn.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.jwn.bookstore.domain.TradeItem;

public interface TradeItemDAO
{
	void batchSave(Collection<TradeItem> items);

	/**
	 * 根据tradeId 获取 TradeItem集合列表
	 * 
	 * @param tradeId
	 * @return
	 */
	Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);
}
