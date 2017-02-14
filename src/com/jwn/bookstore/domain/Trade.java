package com.jwn.bookstore.domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Trade
{
	private int tradeid;
	private int userid;
	private Date tradetime;

	public Trade()
	{
	}
	// 一个Trade关联多个 TradeItem
	private Set<TradeItem> items = new LinkedHashSet<TradeItem>();
	public void setItems(Set<TradeItem> items)
	{
		this.items = items;
	}
	public Set<TradeItem> getItems()
	{
		return items;
	}
	public int getTradeid()
	{
		return tradeid;
	}
	public void setTradeid(int tradeid)
	{
		this.tradeid = tradeid;
	}
	public int getUserid()
	{
		return userid;
	}
	public void setUserid(int userid)
	{
		this.userid = userid;
	}
	public Date getTradetime()
	{
		return tradetime;
	}
	public void setTradetime(Date tradetime)
	{
		this.tradetime = tradetime;
	}
	@Override
	public String toString()
	{
		return "Trade [tradeid=" + tradeid + ", userid=" + userid
				+ ", tradetime=" + tradetime + "]";
	}

}
