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
						//������ϸ �����Book��Ϣ
						item.setBook(bookDAO.getBook(item.getBookid()));
					}
					if(tradeitems!=null&&tradeitems.size()!=0)
					{
						trade.setItems(tradeitems);
					}
				}
				
				//���������ϸΪ�� ���� ������ϸΪ 0 ɾ���˽�������
				if(tradeitems==null||tradeitems.size()==0)
				{
					iterator.remove();//���Ϊ�� ɾ��
				}
			}
		}
		//��ӽ�����ϸ��
		user.setTrades(trades);
		return user;
	}
}
