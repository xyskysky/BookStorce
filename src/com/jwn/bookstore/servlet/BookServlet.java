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
import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.domain.ShoppingCart;
import com.jwn.bookstore.service.BookService;
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
