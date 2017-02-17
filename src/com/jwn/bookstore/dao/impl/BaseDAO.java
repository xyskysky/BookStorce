package com.jwn.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jwn.bookstore.dao.Dao;
import com.jwn.bookstore.db.JDBCTools;
import com.jwn.bookstore.utils.ReflectionUtils;
import com.jwn.bookstore.web.ConnectionContext;

public class BaseDAO<T> implements Dao<T>
{

	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	public BaseDAO()
	{
		clazz = ReflectionUtils.getSuperGenericType(getClass());
	}
	@Override
	public long insert(String sql, Object... params)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long result = 0;
		try
		{
			//connection = JDBCTools.getConnection();
			connection=ConnectionContext.getInstance().get();
			preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			if (params != null)
			{
				// 添加参数
				for (int i = 0; i < params.length; i++)
				{
					preparedStatement.setObject(i + 1, params[i]);
				}
			}
			// 执行数据
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			// 获取主键值
			if (resultSet.next())
			{
				result = resultSet.getLong(1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

		return result;
	}

	@Override
	public void update(String sql, Object... params)
	{
		Connection connection = null;
		try
		{
			connection =ConnectionContext.getInstance().get();
			queryRunner.update(connection, sql, params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

	}

	@Override
	public T query(String sql, Object... params)
	{
		Connection connection = null;
		try
		{
			//connection = JDBCTools.getConnection();
			connection=ConnectionContext.getInstance().get();
			T result = queryRunner.query(connection, sql,
					new BeanHandler<T>(clazz), params);
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... params)
	{
		Connection connection = null;
		try
		{
			//connection = JDBCTools.getConnection();
			connection=ConnectionContext.getInstance().get();
			List<T> result = queryRunner.query(connection, sql,
					new BeanListHandler<T>(clazz), params);
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... params)
	{
		Connection connection = null;
		try
		{
			//connection = JDBCTools.getConnection();
			connection=ConnectionContext.getInstance().get();
			return (V) queryRunner.query(connection, sql, new ScalarHandler(),
					params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void batch(String sql, Object[]... params)
	{
		Connection connection = null;
		try
		{
			//connection = JDBCTools.getConnection();
			connection=ConnectionContext.getInstance().get();
			queryRunner.batch(connection, sql, params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
