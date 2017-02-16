package com.jwn.bookstore.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.jwn.bookstore.dao.TradeDAO;
import com.jwn.bookstore.domain.Trade;

public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO
{

	@Override
	public void insert(Trade trade)
	{
		String sql = "insert into trade(userid,tradetime)values(?,?);";
		long tradeid = insert(sql, trade.getUserid(), trade.getTradetime());
		trade.setTradeid((int) tradeid);
	}

	@Override
	public Set<Trade> getTradeWithUserId(Integer userId)
	{
		String sql="select tradeid,userid,tradetime from trade where userid=? order by tradetime desc;";
		return new LinkedHashSet<Trade>(queryForList(sql, userId));
	}

}
