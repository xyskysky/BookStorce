package com.jwn.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jwn.bookstore.dao.TradeItemDAO;
import com.jwn.bookstore.domain.TradeItem;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO
{

	@Override
	public void batchSave(Collection<TradeItem> items)
	{
		String sql = "insert into tradeitem(bookid,quantity,tradeid)values(?,?,?);";
		Object[][] params = new Object[items.size()][3];
		List<TradeItem> list = new ArrayList<TradeItem>(items);
		for (int i = 0; i < list.size(); i++)
		{
			params[i][0] = list.get(i).getBookid();
			params[i][1] = list.get(i).getQuantity();
			params[i][2] = list.get(i).getTradeid();
		}
		batch(sql, params);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId)
	{
		String sql = "select itemid, bookid,quantity,tradeid from tradeitem where tradeid=?";

		return new LinkedHashSet<TradeItem>(queryForList(sql, tradeId));

	}

}
