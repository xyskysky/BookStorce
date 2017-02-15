package com.jwn.bookstore.service;

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
}
