<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
       您总共买了：${sessionScope.shoppingCart.bookNumber} 本书<br><br>
       您应该付：¥:${sessionScope.shoppingCart.totalMoney}<br><br>
       
       
  <c:if test="${error!=null}">
         <span style="color: red;">${error}</span>
  </c:if>
<form action="bookServlet?method=cash&pageNo=${param.pageNo}" method="post">
   <table cellpadding="10">
       <tr>
          <td>信用卡姓名:</td>
          <td><input type="text" name="username" size="2"></td>
       </tr>
       <tr>
          <td>信用卡号:</td>
          <td><input type="text" name="accountid" size="2"></td>
       </tr>
       <tr>
          <td><input type="submit" value="Submit"></td>
          <td><a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a></td>
       </tr>
   </table>
</form>

       
       
  </center>
</body>
</html>