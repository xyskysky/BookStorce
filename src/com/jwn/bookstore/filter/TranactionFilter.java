package com.jwn.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.jwn.bookstore.db.JDBCTools;
import com.jwn.bookstore.web.ConnectionContext;

/**
 * Servlet Filter implementation class TranactionFilter
 */
@WebFilter(filterName="TranactionFilter",urlPatterns="/*")
public class TranactionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TranactionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Connection connection=null;
		try
		{
			System.out.println("���ݿ�������=============================��ʼ");
			connection=JDBCTools.getConnection();//��ȡ����
			connection.setAutoCommit(false);//��������
			ConnectionContext.getInstance().bind(connection);
			chain.doFilter(request, response);
			connection.commit();
			System.out.println("���ݿ�������=============================�ύ");
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			try
			{
				connection.rollback();
			}
			catch (SQLException e1)
			{
				
				e1.printStackTrace();
			}
		}
		finally {
			JDBCTools.release(connection);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
