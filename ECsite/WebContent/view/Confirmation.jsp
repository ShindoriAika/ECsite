<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CartProductBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="cart" scope="session" class="model.CartBean" type=model.CartBean />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入画面</title>
</head>
<body>
<h1>購入してよろしいでしょうか？？</h1>
	<table>
		<tr>
			<th>商品名</th>
			<th>単価</th>
			<th>数量</th>
		</tr>
		<c:forEach var="product" items="<jsp:getProperty name="cart" property="cartProList"/>">
			<tr>
				<td>${product.pro_name}</td>
				<td>${product.pro_price}</td>
				<td>${product.number}</td>
			</tr>
		</c:forEach>
		<tr>
			<th colspan="2">消費税</th>
			<td><jsp:getProperty name="cart" property="tax"/></td>
		</tr>
		<tr>
			<th colspan="2">合計金額</th>
			<td><jsp:getProperty name="cart" property="total"/></td>
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