package com.jwn.bookstore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jwn.bookstore.dao.AccountDAO;
import com.jwn.bookstore.dao.BookDAO;
import com.jwn.bookstore.dao.TradeDAO;
import com.jwn.bookstore.dao.TradeItemDAO;
import com.jwn.bookstore.dao.UserDAO;
import com.jwn.bookstore.dao.impl.AccountDAOImpl;
import com.jwn.bookstore.dao.impl.BookDAOImpI;
import com.jwn.bookstore.dao.impl.TradeDAOImpl;
import com.jwn.bookstore.dao.impl.TradeItemDAOImpl;
import com.jwn.bookstore.dao.impl.UserDAOImpl;
import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.domain.ShoppingCart;
import com.jwn.bookstore.domain.ShoppingCartItem;
import com.jwn.bookstore.domain.Trade;
import com.jwn.bookstore.domain.TradeItem;
import com.jwn.bookstore.domain.User;
import com.jwn.bookstore.web.BookStoreWebUtils;
import com.jwn.bookstore.web.CriteriaBook;
import com.jwn.bookstore.web.Page;

public class BookService
{
	BookDAO bookDao = new BookDAOImpI();
	public Page<Book> getPage(CriteriaBook criteriaBook,int pageSize)
	{
		return bookDao.getPage(criteriaBook,pageSize);
	}
	public Book getBook(Integer id)
	{
		return bookDao.getBook(id);
	}
	public boolean addShoppingCart(Integer id,ShoppingCart shoppingCart)
	{
		Book book=bookDao.getBook(id);
		if(book!=null)
		{
			System.out.println("book:"+book);
			shoppingCart.addBook(book);
			return true;
		}
		return false;
	}
	public void removeItemFromShoppingCart(ShoppingCart shoppingCart,int id)
	{
		shoppingCart.removeItem(id);
	}
	public void clearShoppingCart(ShoppingCart shoppingCart)
	{
		shoppingCart.clear();
	}
	public Map<String,Object> updateItemQuantity(ShoppingCart shoppingCart,int id,int quantity)
	{
		Map<String, Object> hashMap=new HashMap<String,Object>();
		//查看库存
		int storeNumber = bookDao.getStoreNumber(id);
		if(quantity>storeNumber)
		{
			hashMap.put("status", -1);
			
		}
		else
		{
			shoppingCart.updateItemQuantity(id, quantity);
			hashMap.put("status", 200);
		}
		hashMap.put("storeNumber", storeNumber);
		return hashMap;
	}
	TradeDAO tradeDAO=new TradeDAOImpl();
	UserDAO userDAO=new UserDAOImpl();
	TradeItemDAO tradeItemDAO=new TradeItemDAOImpl();
	AccountDAO accountDAO=new AccountDAOImpl();
	public void cash(HttpServletRequest request, String username,String accountid)
	{
		//第一步修改 图书库存
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		//修改库存和销售
		bookDao.batchUpdateStoreNumberAndSalesAmount(cart.getItems());
		User user=userDAO.getUser(username);
		//添加交易主表
		Trade trade=new Trade();
		trade.setUserid(user.getUserid());
		trade.setTradetime(new Date());
		tradeDAO.insert(trade);;
		
		List<TradeItem> items=new ArrayList<TradeItem>();
		for(ShoppingCartItem cartItem:cart.getItems())
		{
			TradeItem tradeItem=new TradeItem();
			tradeItem.setBookid(cartItem.getBook().getId());
			tradeItem.setQuantity(cartItem.getQuantity());
			tradeItem.setTradeid(trade.getTradeid());
			items.add(tradeItem);
		}
		//添加交易明细
		tradeItemDAO.batchSave(items);
		//扣除信用卡金额
		accountDAO.updateBalance(Integer.parseInt(accountid), cart.getTotalMoney());
		
		//清空购物车
		cart.clear();

	}
}
