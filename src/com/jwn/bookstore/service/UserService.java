package com.jwn.bookstore.service;

import java.util.Iterator;
import java.util.Set;

import com.jwn.bookstore.dao.BookDAO;
import com.jwn.bookstore.dao.TradeDAO;
import com.jwn.bookstore.dao.TradeItemDAO;
import com.jwn.bookstore.dao.UserDAO;
import com.jwn.bookstore.dao.impl.BookDAOImpI;
import com.jwn.bookstore.dao.impl.TradeDAOImpl;
import com.jwn.bookstore.dao.impl.TradeItemDAOImpl;
import com.jwn.bookstore.dao.impl.UserDAOImpl;
import com.jwn.bookstore.domain.Trade;
import com.jwn.bookstore.domain.TradeItem;
import com.jwn.bookstore.domain.User;

public class UserService
{
	UserDAO userDAO = new UserDAOImpl();
	BookDAO bookDAO=new BookDAOImpI();
	public User getUserByUserName(String username)
	{
		return userDAO.getUser(username);
	}
	TradeDAO tradeDAO=new TradeDAOImpl();
	TradeItemDAO tradeItemDAO=new TradeItemDAOImpl();
	
	public User getUserWithTrades(String username)
	{
		User user=userDAO.getUser(username);
		if(user==null)
		{
			return null;
		}
		Set<Trade> trades = tradeDAO.getTradeWithUserId(user.getUserid());
		if(trades!=null)
		{
			Iterator<Trade> iterator = trades.iterator();
			while(iterator.hasNext())
			{
				Trade trade=iterator.next();
				Set<TradeItem> tradeitems = tradeItemDAO.getTradeItemsWithTradeId(trade.getTradeid());
				if(tradeitems!=null)
				{
					for(TradeItem item:tradeitems)
					{
						//交易明细 中添加Book信息
						item.setBook(bookDAO.getBook(item.getBookid()));
					}
					if(tradeitems!=null&&tradeitems.size()!=0)
					{
						trade.setItems(tradeitems);
					}
				}
				
				//如果交易明细为空 或者 交易明细为 0 删除此交易主表
				if(tradeitems==null||tradeitems.size()==0)
				{
					iterator.remove();//如果为空 删除
				}
			}
		}
		//添加交易明细表
		user.setTrades(trades);
		return user;
	}
}
