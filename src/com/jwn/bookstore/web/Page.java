package com.jwn.bookstore.web;

import java.util.List;

/**
 * �û�ҳ���ҳ������
 *
 * @param <T>
 */
public class Page<T>
{
	/**
	 * ��ǰ�ڼ�ҳ
	 */
	private int pageNo;
	/**
	 * ��ǰҳҪ��ʾ��list
	 */
	private List<T> list;
	/**
	 * ÿҳ��ʾ��������¼
	 */
	private int pageSize=5;
	/**
	 * ���ж�������¼
	 */
	private long totalItemNumber;
	
	/**
	 * ���캯����ʼ��pageNo
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
	 * ��ȡÿҳ��ʾ�Ķ�����
	 * @return
	 */
	public int getPageSize()
	{
		return pageSize;
	}
	/**
	 * ���õ�ǰҳҪ��ʾ�ļ�¼����
	 * @param list
	 */
	public void setList(List<T> list)
	{
		this.list = list;
	}
	/**
	 * ��ȡ��ǰҳҪ��ʾ�ļ�¼����
	 * @return
	 */
	public List<T> getList()
	{
		return list;
	}
	/**
	 * ��ȡ�ܹ��ж���ҳ
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
	 * �����ܼ�¼����
	 * @param totalItemNumber
	 */
	public void setTotalItemNumber(long totalItemNumber)
	{
		this.totalItemNumber = totalItemNumber;
	}
	/**
	 * �ж��Ƿ�����һҳ
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
	 * �Ƿ�����һҳ
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
	 * ��ȡ��һҳ��ҳ��
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
	 * ��ȡ��һҳ��ҳ��
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
