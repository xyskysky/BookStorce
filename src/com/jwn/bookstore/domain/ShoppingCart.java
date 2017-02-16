package com.jwn.bookstore.domain;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart
{
	/**
	 * 存放购物车中所以得物品
	 */
	private Map<Integer, ShoppingCartItem> books = new HashMap<Integer, ShoppingCartItem>();

	public Map<Integer, ShoppingCartItem> getBooks()
	{
		return books;
	}
	/**
	 * 检验购物车中是否有 id 指定的商品		
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}		
	/**
	 * 获取所有的明细信息
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems()
	{
		return books.values();
	}
	/**
	 * 修改购物车中的数量
	 * @param id
	 * @param quantity
	 */
	public void updateItemQuantity(Integer id,int quantity)
	{
		ShoppingCartItem item=books.get(id);
		if(item!=null)
		{
		  item.setQuantity(quantity);	
		}
	}
	/**
	 * 获取购物车中的中金额
	 * @return
	 */
	public float getTotalMoney()
	{
		float totalMoney=0.0f;
		for(ShoppingCartItem item:books.values())
		{
			totalMoney+=item.getItemMoney();
		}
		return totalMoney;
	}
	/**
	 * 获取购物车中商品的中数量
	 * @return
	 */
	public int getBookNumber()
	{
		int totalNumber=0;
		for(ShoppingCartItem item:books.values())
		{
			totalNumber+=item.getQuantity();
		}
		return totalNumber;
	}
	/**
	 * 清空购物车
	 */
	public void clear()
	{
		books.clear();
	}
	/**
	 * 购物车是否为空
	 * @return
	 */
	public boolean isEmpty()
	{
		return books.isEmpty();
	}
	/**
	 * 从购物车删除此记录
	 * @param id
	 */
	public void removeItem(Integer id)
	{
		books.remove(id);
	}
	/**
	 * 添加一本书到购物车中
	 * 
	 * @param book
	 */
	public void addBook(Book book)
	{
		ShoppingCartItem shoppingCartItem = books.get(book.getId());
		if (shoppingCartItem == null)
		{
			// 购物车中没有此书籍
			ShoppingCartItem cartItem = new ShoppingCartItem(book);

			books.put(book.getId(), cartItem);
		}
		else
		{
			shoppingCartItem.increment();
		}
	}
}
