package com.jwn.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jwn.bookstore.dao.BookDAO;
import com.jwn.bookstore.dao.impl.BookDAOImpI;
import com.jwn.bookstore.domain.Book;

public class BookDAOImpITest
{

	BookDAO bookDao=new BookDAOImpI();
	@Test
	public void testGetBook()
	{
		Book book = bookDao.getBook(5);
		System.out.println(book);
	}

	@Test
	public void testGetPage()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalBookNumber()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageList()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetStoreNumber()
	{
		fail("Not yet implemented");
	}

}
