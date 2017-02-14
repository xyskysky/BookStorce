package com.jwn.bookstore.domain;

public class TradeItem
{
	private int itemid;
	private int bookid;
	private int tradeid;
	private int quantity;
	private Book book;
	public TradeItem()
	{
	}
	//有参数构造函数
	public TradeItem(int itemid, int bookid, int tradeid, int quantity)
	{
		super();
		this.itemid = itemid;
		this.bookid = bookid;
		this.tradeid = tradeid;
		this.quantity = quantity;
	}

	public int getItemid()
	{
		return itemid;
	}
	public void setItemid(int itemid)
	{
		this.itemid = itemid;
	}
	public int getBookid()
	{
		return bookid;
	}
	public void setBookid(int bookid)
	{
		this.bookid = bookid;
	}
	public int getTradeid()
	{
		return tradeid;
	}
	public void setTradeid(int tradeid)
	{
		this.tradeid = tradeid;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public Book getBook()
	{
		return book;
	}
	public void setBook(Book book)
	{
		this.book = book;
	}
	@Override
	public String toString()
	{
		return "TradeItem [itemid=" + itemid + ", bookid=" + bookid
				+ ", tradeid=" + tradeid + ", quantity=" + quantity + ", book="
				+ book + "]";
	}
	
	
}
