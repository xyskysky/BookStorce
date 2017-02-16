package com.jwn.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.jwn.bookstore.domain.TradeItem;

public interface TradeItemDAO
{
	void batchSave(Collection<TradeItem> items);

	/**
	 * ����tradeId ��ȡ TradeItem�����б�
	 * 
	 * @param tradeId
	 * @return
	 */
	Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);
}
