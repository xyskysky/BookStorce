<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("a").click(function() {
			var serializeVal = $(":hidden").serialize();

			this.href = this.href + "&" + serializeVal;
		});
		//转到处理
		$("#pageNo")
				.change(
						function() {
							var pageNoStr = $(this).val();
							pageNoStr = $.trim(pageNoStr);//去空格
							var reg = /^\d+$/g;//正则表达式
							var pageNo = 0;
							var flag = false;
							if (reg.test(pageNoStr)) {
								//输了的是数据
								pageNo = parseInt(pageNoStr);
								if (pageNo >= 1
										&& pageNo <= parseInt("${bookpage.totalPageNumber}")) {
									flag = true;
								}
							}
							if (!flag) {
								alert("你输了的数据不合法");
								$(this).val("");
								return false;
							}
							//进行页面跳转
							var herf = "bookServlet?method=getBooks&pageNo="
									+ pageNo + "&" + $(":hidden").serialize();
							window.location.href = herf;

						});
	});
</script>
</head>
<body>
	<center>
		<input type="hidden" name="minPrice" value="${param.minPrice}">
		<input type="hidden" name="maxPrice" value="${param.maxPrice}">
		<h1>加入购物车</h1>
		<c:if test="${param.title!=null}">
		  您已经将：<span style="color: green;">${param.title}</span>添加到购物车
		  	<br>
		</c:if>
	
		<c:if test="${!empty sessionScope.shoppingCart.books}">
		   您的购物车中有：${sessionScope.shoppingCart.bookNumber} 件商品&nbsp;
		   <a href="">查看购物车</a>
		   	<br>
		</c:if>
        <br>
		<form action="bookServlet?method=getBooks" method="post">
			Price: <input type="text" name="minPrice" />--- <input type="text"
				name="maxPrice"> <input id="btn" type="submit" value="查找">

		</form>
		<br>
		<table cellpadding="10" border="1" cellspacing="1">
			<thead>
				<tr>
					<th>名称</th>
					<th>作者</th>
					<th>单价</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${bookpage.list}" var="book">
				<tr>
					<td><a
						href="bookServlet?method=getBook&id=${book.id}&pageNo=${bookpage.pageNo}">${book.title}</a></td>
					<td>${book.author}</td>
					<td>${book.price }</td>
					<td><a
						href="bookServlet?method=addToCart&id=${book.id}&pageNo=${bookpage.pageNo}&title=${book.title}">添加购物车</a></td>
				</tr>
			</c:forEach>
		</table>
		<br> 总共${bookpage.totalPageNumber}页
		&nbsp;&nbsp;当前第${bookpage.pageNo}页 &nbsp;&nbsp;
		<c:if test="${bookpage.hasPrev}">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
		  &nbsp;
		  <a href="bookServlet?method=getBooks&pageNo=${bookpage.pageNo-1}">上一页</a>
		</c:if>
		<c:if test="${bookpage.hasNext }">
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.pageNo+1}">下一页</a>
		 &nbsp;
		 <a
				href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber}">末页</a>
		</c:if>
		&nbsp; 转到<input id="pageNo" size="1" type="text">页
	</center>

</body>
</html>