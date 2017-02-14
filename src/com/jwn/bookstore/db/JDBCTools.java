package com.jwn.bookstore.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.jwn.bookstore.exception.DBException;
import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * JDBC工具类
 */
public class JDBCTools
{
	private static DataSource dataSource=null;
	static
	{
		dataSource=new ComboPooledDataSource("javawebapp");
	}
	public static Connection getConnection()
	{
		try
		{
			return dataSource.getConnection();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}
	public static void release(ResultSet resultSet,Statement statement,Connection connection)
	{
		if(resultSet!=null)
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException e)
			{
				
				e.printStackTrace();
				throw new DBException("数据库连接错误!");
			}
		}
		if(statement!=null)
		{
			try
			{
				statement.close();
			}
			catch (SQLException e)
			{
				
				e.printStackTrace();
				throw new DBException("数据库连接错误!");
			}
		}
		if(connection!=null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				
				e.printStackTrace();
				throw new DBException("数据库连接错误!");
			}
		}
	}
	public static void release(Statement statement,Connection connection)
	{
		release(null, statement,connection);;
	}
	public static void release(Connection connection)
	{
		release(null,connection);
	}
}
