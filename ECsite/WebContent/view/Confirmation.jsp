<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CartProductBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入画面</title>
	</head>
	<body>
	<jsp:include page="Header.jsp"/>
	<h1>購入してよろしいでしょうか？？</h1>
		<table>
			<tr>
				<th>商品名</th>
				<th>単価</th>
				<th>数量</th>
			</tr>
			<c:forEach var="product" items="${cart.cartProList}">
				<tr>
					<td>${product.proName}</td>
					<td>${product.proPrice}</td>
					<td>${product.number}</td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2">消費税</th>
				<td>${cart.tax}</td>
			</tr>
			<tr>
				<th colspan="2">合計金額</th>
				<td>${cart.total}</td>
			</tr>
		</table>
		<form action="/CategoryServlet" method="POST">
			<input type="submit" value="いいえ">
		</form>
		<form action="/ConfirmationServlet" method="POST">
			<input type="submit" value="はい">
		</form>
	</body>
</html>