<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.ProductBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<td>${product.proPrice}</td>
			</tr>
			<tr>
				<th>在庫</th>
				<td>${product.stockNo}</td>
			</tr>
			<tr>
				<th>商品説明</th>
				<td>${product.proMsg}</td>
			</tr>
		</table>
		<form action="/ECsite/ProductDetailServlet" method="POST">
			<input type="number" name="number" value="0" min="1" max="${product.stockNo}">
			<input type="hidden" name="proCd" value="${product.proCd}">
			<input type="hidden" name="proName" value="${product.proName}">
			<input type="hidden" name="proPrice" value="${product.proPrice}">
			<input type="hidden" name="stockNo" value="${product.stockNo}">
			<input type="submit" value="カートへ">
		</form>
		<form action="/ECsite/CategoryServlet" method="POST">
			<input type="submit" value="もどる">
		</form>
	</body>
</html>