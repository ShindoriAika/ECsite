<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CartBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入画面</title>
</head>
<body>
<% int total = 0; %>
<h1>購入してよろしいでしょうか？？</h1>
	<table>
		<tr>
			<th>商品名</th>
			<th>単価</th>
			<th>数量</th>
		</tr>
		<c:forEach var="cartList" items="${cartList}">
			<tr>
				<td>${cartList.pro_name}</td>
				<td>${cartList.pro_price}</td>
				<td>${cartList.number}</td>
			</tr>
			<% ${total += (cartList.pro_price)*(cartList.number)} %>
		</c:forEach>
		<tr>
			<th colspan="2">消費税</th>
			<td><%= (int)(total*(1.1))-total %></td>
		</tr>
		<tr>
			<th colspan="2">合計金額</th>
			<td><%= (int)(total*(1.1)) %></td>
		</tr>
	</table>
	<form action="http://localhost:8080/ECsite/CategoryServlet" method="POST">
		<input type="submit" value="いいえ">
	</form>
	<form action="http://localhost:8080/ECsite/ConfirmationServlet" method="POST">
		<input type="submit" value="はい">
	</form>
</body>
</html>