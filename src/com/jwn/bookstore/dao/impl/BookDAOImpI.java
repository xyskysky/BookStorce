package com.jwn.bookstore.dao.impl;

import java.util.List;

import com.jwn.bookstore.dao.BookDAO;
import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.web.CriteriaBook;
import com.jwn.bookstore.web.Page;

public class BookDAOImpI extends BaseDAO<Book> implements BookDAO
{

	@Override
	public Book getBook(int id)
	{
		String sql="SELECT id,author,title,price,publishingDate,salesAmount,"
				+ "storeNumber,remark FROM mybooks where id=?;";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb,int pageSize)
	{
		Page<Book> page=new Page<Book>(cb.getPageNo());
		page.setPageSize(pageSize);
		//设置总记录条数
		page.setTotalItemNumber(getTotalBookNumber(cb));
		//校验
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, pageSize));
		
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb)
	{
		String sql="SELECT COUNT(id) FROM mybooks WHERE price>=? AND price<=?;";
		return getSingleVal(sql,cb.getMinPrice(),cb.getMaxPrice());
	}

	/**
	 * MySQL 分页使用 LIMIT, 其中 fromIndex 从 0 开始。 
	 */
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize)
	{
		String sql="SELECT id,author,title,price,publishingDate,salesAmount,storeNumber,"
				+ "remark FROM mybooks where price>=? AND price<=? limit ?,?;";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),
				(cb.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(Integer id)
	{
		String sql="SELECT storeNumber FROM mybooks WHERE id=?;";
		return getSingleVal(sql,id);
	}
	
	

}
