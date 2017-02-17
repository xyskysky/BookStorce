package com.jwn.bookstore.web;

import java.sql.Connection;

public class ConnectionContext
{
	private ConnectionContext()
	{}
	private static ConnectionContext instance=new ConnectionContext();
	public static ConnectionContext getInstance()
	{
		return instance;
	}
	
	private ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
	public void bind(Connection connection)
	{
		threadLocal.set(connection);
	}
	public Connection get()
	{
		return threadLocal.get();
	}
	public void remove()
	{
		threadLocal.remove();
	}
}
