<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.ProductBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品詳細画面</title>
</head>
	<body>
		<jsp:include page="Header.jsp"/>
		<h1>商品紹介</h1>
		<table>
			<tr>
				<td colspan="2"><img src="./img/${product.proImg}"></td>
			</tr>
			<tr>
				<th>商品名</th>
				<td>${product.proName}</td>
			</tr>
			<tr>
				<th>カテゴリ</th>
				<td>${product.catName}</td>
			</tr>
			<tr>
				<th>価格</th>
				<td><fmt:formatNumber value="${product.proPrice}" type="currency" maxFractionDigits="0" currencySymbol="\\" /></td>
			</tr>
			<tr>
				<th>在庫</th>
				<c:if test="${product.stockNo != 0}">
					<td>${product.stockNo}</td>
				</c:if>
				<c:if test="${product.stockNo == 0}">
					<td>SOLD OUT</td>
				</c:if>
			</tr>
			<tr>
				<th>商品説明</th>
				<td>${product.proMsg}</td>
			</tr>
		</table>
		<c:if test="${product.stockNo != 0}">
			<form action="/ECsite/ProductDetailServlet" method="POST">
				<input type="number" name="number" value="0" min="1" max="${product.stockNo}">
				<input type="hidden" name="proCd" value="${product.proCd}">
				<input type="hidden" name="proName" value="${product.proName}">
				<input type="hidden" name="proPrice" value="${product.proPrice}">
				<input type="hidden" name="stockNo" value="${product.stockNo}">
				<input type="submit" value="カートへ">
			</form>
		</c:if>
		<form action="/ECsite/CategoryServlet" method="POST">
			<input type="submit" value="もどる">
		</form>
	</body>
</html>