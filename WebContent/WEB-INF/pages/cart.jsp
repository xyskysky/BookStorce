<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/basetitle.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<%@ include file="/commons/condition.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			var flag = confirm("你是否要删除:" + title + " 吗?");
			return flag;

		});
		$(":text").change(function() {

			
			var orginValue = parseInt($(this).attr("name"));
			var quantityVal = $.trim($(this).val());
			//判断数据的合法性
			var reg = /^\d+$/g;
			var quantity = -1;
			var flag = false;
			if (reg.test(quantityVal)) {
				quantity = parseInt(quantityVal);
				if (quantity >= 0) {
					flag = true;
				}
			}
			if (!flag) {
				alert("您数量的数据不合法!");
				$(this).val(orginValue);
				return false;
			}
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			if (quantity == 0) {
				var flag2 = confirm("你是否要删除:" + title + " 吗?");
				if (flag2) {
					var $a = $tr.find("td:last").find("a");
					$a[0].onclick();

				}
				return false;
			}
			var flag = confirm("您确认要修改：" + title + " 的数量吗？");
			if (!flag) {
				$(this).val(orginValue);
				return false;
			}
			//调用ajax删除数据
			var url = "bookServlet";
			var id = $(this).attr("title");
			var args = {
				"method" : "updateItemQuantity",
				"id" : id,
				"quantity" : quantity,
				"time" : new Date()
			};
			var $current=$(this);
			$.post(url, args, function(data) {

				var state=parseInt(data.status);
				if (state==-1) {
                    
					alert("当前库存为："+data.storeNumber+"!库存不足!");
					$current.val(orginValue);
				} else if(state==200) {
                    $("#bookNumber").text(data.bookNumber);
                    $("#totalMoney").text(data.totalMoney);
				}
			},"json");

		});
	});
</script>
</head>
<body>
	<center>
		您购物车中有 <span id="bookNumber">${sessionScope.shoppingCart.bookNumber}</span>书<br> <br>
		<table cellpadding="10" border="1" cellspacing="1">
			<thead>
				<tr>
					<th>书名</th>
					<th>数量</th>
					<th>价格</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${sessionScope.shoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title}</td>
					<td><input name="${item.quantity}" title="${item.book.id}"
						type="text" size="1" value="${item.quantity}"></td>
					<td>${item.book.price}</td>
					<td><a class="delete"
						href="bookServlet?method=delete&pageNo=${param.pageNo}&id=${item.book.id}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">总金额:¥<span id="totalMoney">${sessionScope.shoppingCart.totalMoney}</span></td>
			</tr>
			<tr>
				<td colspan="4"><a
					href="bookServlet?method=getBooks&pageNo=${param.pageNo}">继续购物</a>&nbsp;&nbsp;&nbsp;
					<a href="bookServlet?method=clear&pageNo=${param.pageNo}">清空购物车</a>&nbsp;&nbsp;&nbsp;
					<a href="bookServlet?method=forwardPage&page=cash&pageNo=${param.pageNo}">结账</a></td>
			</tr>
		</table>


	</center>
</body>
</html>