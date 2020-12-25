<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CartProductBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート画面</title>
	</head>
	<body>
		<jsp:include page="Header.jsp"/>
		<h1>カート</h1>
		<p>${message}</p>
		<table>
			<tr>
				<td></td>
				<th>商品名</th>
				<th>単価</th>
				<th>数量</th>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="product" items="${cart.cartProList}">
				<tr>
					<td>
						<form action="/CartServlet" method="POST">
							<input type="submit" value ="×">
							<input type="hidden" name="proCd" value="${product.proCd}">
							<input type="hidden" name="flg" value="2">
						</form>
					</td>
					<td>${product.proName}</td>
					<td>${product.proPrice}</td>
					<td>${product.number}</td>
					<form action="/CartServlet" method="POST">
						<input type="hidden" name="proCd" value="${product.proCd}">
						<input type="hidden" name="flg" value="3">
						<td><input type="number" name="number" value="0" min="1" max="${product.stockNo}"></td>
						<td><input type="submit" value="変更"></td>
					</form>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="3">消費税</th>
				<td colspan="3">${cart.tax}</td>
			</tr>
			<tr>
				<th colspan="3">合計金額</th>
				<td colspan="3">${cart.total}</td>
			</tr>
		</table>
		<form action="/CategoryServlet" method="POST">
			<input type="submit" value="買い物を続ける">
		</form>
		<form action="/view/Confirmation.jsp" method="POST">
			<input type="submit" value="購入">
		</form>
	</body>
</html>