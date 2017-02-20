<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/basetitle.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>姓名:${user.username}</h2>

		<c:forEach items="${user.trades}" var="trade">
			<table border="1" cellpadding="10" cellspacing="0">
				<tr>
					<td>交易编号:${trade.tradeid}</td>
					<td colspan="4">日期:<fmt:formatDate value="${trade.tradetime }" pattern="yyyy年MM月dd日 HH时mm分ss秒"/>
					</td>
				</tr>
				<tr>
				   <td>编号</td>
				   <td>书名</td>
				   <td>订购数量</td>
				   <td>单价</td>
				   <td>合计</td>
				</tr>
				<c:forEach items="${trade.items}" var="item">
                    <tr>
                         <td>${item.itemid}</td>
                         <td>${item.book.title}</td>
                         <td>${item.quantity}</td>
                         <td>${item.book.price}</td>
                         <td>${item.book.price*item.quantity}</td>
                    </tr>
				</c:forEach>
			</table>
			<br>
		</c:forEach>
	</center>
</body>
</html>