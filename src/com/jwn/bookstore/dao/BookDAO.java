package com.jwn.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.domain.ShoppingCartItem;
import com.jwn.bookstore.web.CriteriaBook;
import com.jwn.bookstore.web.Page;

public interface BookDAO
{
	/**
	 * 根据ID编号获取单个书本信息
	 * 
	 * @param id
	 * @return
	 */
	Book getBook(int id);
	/**
	 * 根据传入的CriteriaBook 返回对于的Page对象
	 * @param cb
	 * @return
	 */
	Page<Book> getPage(CriteriaBook cb,int pageSize);
	
	/**
	 * 根据传入的CriteraBook 返回其对应的记录条数
	 * @param cb
	 * @return
	 */
	long getTotalBookNumber(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回当前页对应的 List 
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	List<Book> getPageList(CriteriaBook cb,int pageSize);
	
	/**
	 * 返回指定 id 的 book 的 storeNumber 字段的值
	 * @param id
	 * @return
	 */
	int getStoreNumber(Integer id);
	
	/**
	 * 根据传入的 ShoppingCartItem 的集合, 
	 * 批量更新 books 数据表的 storenumber 和 salesnumber 字段的值
	 * @param items
	 */
	void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
