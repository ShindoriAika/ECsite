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
<h1>商品紹介</h1>
	<table>
		<tr>
			<td colspan="2">${product.pro_img}</td>
		</tr>
		<tr>
			<th>商品名</th>
			<td>${product.pro_name}</td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td>${product.cat_name}</td>
		</tr>
		<tr>
			<th>価格</th>
			<td>${product.pro_price}</td>
		</tr>
		<tr>
			<th>在庫</th>
			<td>${product.stock_no}</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td>${product.pro_msg}</td>
		</tr>
	</table>
	<form action="http://localhost:8080/ECsite/ProductDetailServlet" method="POST">
		<input type="number" name="number" value="0" min="1" max="${product.stock_no}">
		<input type="hidden" name="pro_cd" value="${product.pro_cd}">
		<input type="hidden" name="pro_name" value="${product.pro_name}">
		<input type="hidden" name="pro_price" value="${product.pro_price}">
		<input type="hidden" name="stock_no" value="${product.stock_no}">
		<input type="submit" value="カートへ">
	</form>
	<form action="http://localhost:8080/ECsite/CategoryServlet" method="POST">
		<input type="submit" value="もどる">
	</form>

</body>
</html>