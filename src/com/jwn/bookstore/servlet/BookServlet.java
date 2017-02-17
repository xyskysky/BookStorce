package com.jwn.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jwn.bookstore.domain.Account;
import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.domain.ShoppingCart;
import com.jwn.bookstore.domain.ShoppingCartItem;
import com.jwn.bookstore.domain.User;
import com.jwn.bookstore.service.AccountService;
import com.jwn.bookstore.service.BookService;
import com.jwn.bookstore.service.UserService;
import com.jwn.bookstore.utils.TextUtils;
import com.jwn.bookstore.web.BookStoreWebUtils;
import com.jwn.bookstore.web.CriteriaBook;
import com.jwn.bookstore.web.Page;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	public BookServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String methodName = request.getParameter("method");
		try
		{
			// 利用反射获取方法对象
			Method method = getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			// 调用方法
			method.invoke(this, request, response);
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

	}
	UserService userService=new UserService();
	//进行结账
	protected void cash(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("cash====================================执行了");
		String username=request.getParameter("username");//用户名
		String accountid=request.getParameter("accountid");//信用卡号
		
		
		System.out.println("username:"+username+"===========accountid:"+accountid);
		//先验证是否为空
		StringBuilder errors=validationFormField(username,accountid);
		//表单验证通过
		if(errors.toString().equals(""))
		{
			//验证用户名和信用卡账号是否匹配
			errors=validationUser(username,accountid);
			if(errors.toString().equals(""))
			{
				//验证库存
				errors=validateBookStoreNumber(request);
				if(errors.toString().equals(""))
				{
					//验证账号的钱数
					errors=validateBalance(request,accountid);
				}
			}
		}
		//验证没有通过
		if(!errors.toString().equals(""))
		{
			request.setAttribute("error", errors.toString());
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
			return;
		}
		System.out.println("errors:==================="+errors.toString());
		//进行结账处理
		bookService.cash(request,username,accountid);
		response.sendRedirect(request.getContextPath()+"/success.jsp");
		
	}

	AccountService accountService=new AccountService();
	private StringBuilder validateBalance(HttpServletRequest request,
			String accountid)
	{
		StringBuilder errors=new StringBuilder("");
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		Account account=accountService.getAccount(Integer.parseInt(accountid));
		if(account!=null)
		{
			if(account.getBalance()<cart.getTotalMoney())
			{
				errors.append("您的余额不足!");
			}
		}
		else
		{
			errors.append("信用卡号不存在!");
		}
		return errors;
	}

	private StringBuilder validateBookStoreNumber(HttpServletRequest request)
	{
		ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
		StringBuilder errors=new StringBuilder("");
		
		for(ShoppingCartItem item:shoppingCart.getItems())
		{
			Book book=bookService.getBook(item.getBook().getId());
			if(item.getQuantity()>book.getStoreNumber())
			{
				errors.append(book.getTitle()+":库存不足,剩余库存："+book.getStoreNumber());
			}
		}
		
		
		return errors;
	}

	private StringBuilder validationUser(String username, String accountid)
	{
		StringBuilder errors=new StringBuilder("");
		User user=userService.getUserByUserName(username);
		boolean flag=false;
		if(user!=null)
		{
			int accountid2 = user.getAccountid();
			if(accountid.trim().equals(""+accountid2))
			{
				flag=true;
			}
		}
		if(!flag)
		{
			errors.append("用户名和账号不匹配<br>");
		}
		return errors;
	}

	private StringBuilder validationFormField(String username, String accountid)
	{
		StringBuilder errors=new StringBuilder("");
		if(TextUtils.isEmpty(username))
		{
			errors.append("用户姓名不能为空!<br>");
		}
		if(TextUtils.isEmpty(accountid))
		{
			errors.append("信用卡号不能为空!<br>");
		}
		return errors;
	}

	protected void updateItemQuantity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String idStr=request.getParameter("id");
		String quantityStr=request.getParameter("quantity");
		int id=-1;
		int quantity=-1;
		

		try
		{
			quantity=Integer.parseInt(quantityStr);
			id=Integer.parseInt(idStr);
		}
		catch (NumberFormatException e)
		{
		}
		ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
		Map<String, Object> hashMap=new HashMap<String,Object>();
		if(quantity>0 &&id>0)
		{
			hashMap=bookService.updateItemQuantity(shoppingCart, id, quantity);	
		}
		else
		{
			hashMap.put("status", 0);
			hashMap.put("storeNumber", 0);
		}
		
		
		hashMap.put("bookNumber", shoppingCart.getBookNumber());
		hashMap.put("totalMoney", shoppingCart.getTotalMoney());
		Gson gson=new Gson();
		String json = gson.toJson(hashMap);
		System.out.println(json);
		response.setContentType("text/javascript");
		response.getWriter().print(json);
		
	}
	
	
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
	    bookService.clearShoppingCart(shoppingCart);
	    request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request, response);
	}
	
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String idStr=request.getParameter("id");//图书的ID
		
		int id=-1;
		try
		{
			id=Integer.parseInt(idStr);
		}
		catch (NumberFormatException e)
		{
		}
		//先获取购物车
		ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
		bookService.removeItemFromShoppingCart(shoppingCart, id);
		if(shoppingCart.isEmpty())
		{
			request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
		}
		
		
	}
	protected void forwardPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String page=request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/"+page+".jsp").forward(request, response);
	}
	//添加购物车
	protected void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String idStr=request.getParameter("id");
		int id=-1;
		boolean flag=false;
		try
		{
			id=Integer.parseInt(idStr);
		}
		catch (NumberFormatException e)
		{
		}
		if(id>0)
		{
			ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);//获取购物车
			//添加到购物车
			flag=bookService.addShoppingCart(id, shoppingCart);
		}
		if(flag)
		{
			getBooks(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/error.jsp");
		
		
	}
	//获取单个书的信息
	protected void getBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String idStr=request.getParameter("id");
		
		int id=-1;
		try
		{
			id=Integer.parseInt(idStr);
		}
		catch (NumberFormatException e)
		{
		}
		Book book=null;
		if(id>0)
		{
			 book=bookService.getBook(id);
		}
		if(book==null)
		{
			//跳转到错误页面
			System.out.println("getContextPath:"+request.getContextPath());
			response.sendRedirect(request.getContextPath()+"/error.jsp");
			return;
		}
		request.setAttribute("book", book);
		
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}
	protected void getBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// 获取参数
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");

		int pageNo = 1;
		float minPrice = 0;
		float maxPrice =Float.MAX_VALUE;

		try
		{
			if (!TextUtils.isEmpty(pageNoStr))
			{
				pageNo = Integer.parseInt(pageNoStr);
			}
		}
		catch (NumberFormatException e)
		{
		}
		try
		{
			if (!TextUtils.isEmpty(minPriceStr))
			{
				minPrice = Float.parseFloat(minPriceStr);
			}
		}
		catch (NumberFormatException e)
		{
		}
		try
		{
			if (!TextUtils.isEmpty(maxPriceStr))
			{
				maxPrice = Float.parseFloat(maxPriceStr);
			}
		}
		catch (NumberFormatException e)
		{
		}
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice,
				pageNo);
		Page<Book> page = bookService.getPage(criteriaBook,3);
        
		request.setAttribute("bookpage", page);
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp")
				.forward(request, response);
	}

}
