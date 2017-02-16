package com.jwn.bookstore.domain;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart
{
	/**
	 * ��Ź��ﳵ�����Ե���Ʒ
	 */
	private Map<Integer, ShoppingCartItem> books = new HashMap<Integer, ShoppingCartItem>();

	public Map<Integer, ShoppingCartItem> getBooks()
	{
		return books;
	}
	/**
	 * ���鹺�ﳵ���Ƿ��� id ָ������Ʒ		
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}		
	/**
	 * ��ȡ���е���ϸ��Ϣ
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems()
	{
		return books.values();
	}
	/**
	 * �޸Ĺ��ﳵ�е�����
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
	 * ��ȡ���ﳵ�е��н��
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
	 * ��ȡ���ﳵ����Ʒ��������
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
	 * ��չ��ﳵ
	 */
	public void clear()
	{
		books.clear();
	}
	/**
	 * ���ﳵ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty()
	{
		return books.isEmpty();
	}
	/**
	 * �ӹ��ﳵɾ���˼�¼
	 * @param id
	 */
	public void removeItem(Integer id)
	{
		books.remove(id);
	}
	/**
	 * ���һ���鵽���ﳵ��
	 * 
	 * @param book
	 */
	public void addBook(Book book)
	{
		ShoppingCartItem shoppingCartItem = books.get(book.getId());
		if (shoppingCartItem == null)
		{
			// ���ﳵ��û�д��鼮
			ShoppingCartItem cartItem = new ShoppingCartItem(book);

			books.put(book.getId(), cartItem);
		}
		else
		{
			shoppingCartItem.increment();
		}
	}
}
