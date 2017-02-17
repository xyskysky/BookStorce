package com.jwn.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwn.bookstore.domain.User;
import com.jwn.bookstore.service.UserService;
import com.jwn.bookstore.utils.TextUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public UserServlet()
	{
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		doPost(request, response);
	}

	UserService userService=new UserService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
       String username=request.getParameter("username");
       System.out.println("username:"+username);
    
       if(TextUtils.isEmpty(username))
       {
    	   request.setAttribute("error","用户名必须输入");
    	  request.getRequestDispatcher("user.jsp").forward(request, response);
    	  return;
       }
       User user = userService.getUserWithTrades(username);
       if(user==null)
       {
    	   response.sendRedirect(request.getContextPath()+"/error.jsp");
    	   return;
       }
       request.setAttribute("user", user);
       request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);
       
       
	}

}
