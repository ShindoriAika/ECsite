<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CategoryBean,model.ProductBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
</head>
<body>
	<h1>検索</h1>
	<p>${errorMessage}</p>
	<form action="http://localhost:8080/ECsite/SearchServlet" method="post">
		<select name="cat_name">
			<option value="">すべて</option>
			<c:forEach var="category" items="${category}">
				<option value="${category.cat_id}">${category.cat_name}</option>
			</c:forEach>
		</select>

		<input type="text" name="keyword">
		<input type="submit" value="検索">

		<c:if test="${not empty proList}">
			<table>
				<tr>
					<td>商品名</td>
					<td>価格</td>
					<td>在庫</td>
				</tr>
				<c:forEach var="product" items="${product}">
					<tr>
						<td><a href="http://localhost:8080/ECsite/SearchServlet?pro_cd=${product.pro_cd}">${product.pro_name}</a></td>
						<td>${product.pro_price}</td>
						<td>${product.stock_no}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</form>

</body>
</html>