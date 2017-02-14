package com.jwn.bookstore.web;

import java.util.List;

/**
 * 用户页面分页的类型
 *
 * @param <T>
 */
public class Page<T>
{
	/**
	 * 当前第几页
	 */
	private int pageNo;
	/**
	 * 当前页要显示的list
	 */
	private List<T> list;
	/**
	 * 每页显示多少条记录
	 */
	private int pageSize=5;
	/**
	 * 共有多少条记录
	 */
	private long totalItemNumber;
	
	/**
	 * 构造函数初始化pageNo
	 * @param pageNo
	 */
	public Page(int pageNo)
	{
		super();
		this.pageNo=pageNo;
	}
	public int getPageNo()
	{
		if(pageNo<0)
		{
			pageNo=1;
		}
		if(pageNo>getTotalPageNumber())
		{
			pageNo=getTotalPageNumber();
		}
		return pageNo;
	}
	/**
	 * 获取每页显示的多少条
	 * @return
	 */
	public int getPageSize()
	{
		return pageSize;
	}
	/**
	 * 设置当前页要显示的记录条数
	 * @param list
	 */
	public void setList(List<T> list)
	{
		this.list = list;
	}
	/**
	 * 获取当前页要显示的记录条数
	 * @return
	 */
	public List<T> getList()
	{
		return list;
	}
	/**
	 * 获取总共有多少页
	 * @return
	 */
	public int getTotalPageNumber()
	{
		int pageNumber=(int)totalItemNumber/pageSize;
		if(totalItemNumber%pageNumber!=0)
		{
			pageNumber++;
		}
		return pageNumber;
	}
	/**
	 * 设置总记录条数
	 * @param totalItemNumber
	 */
	public void setTotalItemNumber(long totalItemNumber)
	{
		this.totalItemNumber = totalItemNumber;
	}
	/**
	 * 判断是否有下一页
	 * @return
	 */
	public boolean isHasNext()
	{
		if(pageNo<getTotalPageNumber())
		{
			return true;
		}
		return false;
	}
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPrev()
	{
		if(pageNo>1)
		{
			return true;
		}
		return false;
	}
	/**
	 * 获取上一页的页码
	 * @return
	 */
	public int getPrevPage()
	{
		if(isHasPrev())
		{
			return getPageNo()-1;
		}
		return getPageNo();
	}
	/**
	 * 获取下一页的页码
	 * @return
	 */
	public int getNextPage()
	{
		if(isHasNext())
		{
			return getPageNo()+1;
		}
		return getPageNo();
	}
	
}
