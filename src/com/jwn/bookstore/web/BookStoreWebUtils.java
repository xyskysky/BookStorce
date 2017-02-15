package com.jwn.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jwn.bookstore.domain.ShoppingCart;

public class BookStoreWebUtils
{
	/**
	 * ��ȡ���ﳵ����: �� session �л�ȡ, �� session ��û�иĶ���.
	 * �򴴽�һ���µĹ��ﳵ����, ���뵽 session ��.
	 * ����, ��ֱ�ӷ���. 
	 * @param request
	 * @return
	 */
    public static ShoppingCart getShoppingCart(HttpServletRequest request)
    {
    	HttpSession session = request.getSession();
    	ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
    	if(shoppingCart==null)
    	{
    		shoppingCart=new ShoppingCart();
    		session.setAttribute("shoppingCart", shoppingCart);
    		
    	}
    	return shoppingCart;
    }
}
