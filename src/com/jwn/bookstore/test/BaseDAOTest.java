package com.jwn.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jwn.bookstore.dao.impl.BaseDAO;

public class BaseDAOTest
{

	BaseDAO baseDao=new BaseDAO();
	@Test
	public void testInsert()
	{
		String sql="insert into account(balance)values(?);";
		long index = baseDao.insert(sql, 5000);
		System.out.println(index+"=====");
	}

	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testQuery()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testQueryForList()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetSingleVal()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testBatch()
	{
		fail("Not yet implemented");
	}

}
