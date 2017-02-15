package com.jwn.bookstore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils
{
	public static boolean isEmpty(String str)
	{
		if (str == null || str.length() <= 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * ÅÐ¶Ï×Ö·ûÊÇ·ñÊÇÊý×Ö
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches())
		{
			return false;
		}
		return true;
	}

}
