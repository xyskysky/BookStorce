package com.jwn.bookstore.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.jwn.bookstore.dao.Dao;
import com.jwn.bookstore.utils.ReflectionUtils;

public class BaseDAO<T> implements Dao<T>
{

	private QueryRunner queryRunner=new QueryRunner();
	private Class<T> clazz;
	public BaseDAO()
	{
		clazz=ReflectionUtils.getSuperGenericType(getClass());
	}
	@Override
	public long insert(String sql, Object... params)
	{
	
		return 0;
	}

	@Override
	public void update(String sql, Object... params)
	{
	}

	@Override
	public T query(String sql, Object... params)
	{
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... params)
	{
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... params)
	{
		return null;
	}

	@Override
	public void batch(String sql, Object[]... params)
	{
	}

}
