<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/commons/basetitle.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>

<%@ include file="/commons/condition.jsp" %>
</head>
<body>
<center>
          <h2>您的购物车已经清空</h2><br><br>
          <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a>
</center>
</body>
</html>