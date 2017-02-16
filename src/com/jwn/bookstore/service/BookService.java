package com.jwn.bookstore.service;

import java.util.HashMap;
import java.util.Map;

import com.jwn.bookstore.dao.BookDAO;
import com.jwn.bookstore.dao.impl.BookDAOImpI;
import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.domain.ShoppingCart;
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
		//²é¿´¿â´æ
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
}
