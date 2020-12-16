<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.ProductBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細画面</title>
</head>
<body>
<% ProductBean pb = new ProductBean(); %>
<% ArrayList<ProductBean> list = (ArrayList<ProductBean>)request.getAttribute("product"); %>
<% pb = list.get(0); %>
<h1>商品紹介</h1>
	<table>
		<tr>
			<td colspan="2"><%= pb.getPro_img() %></td>
		</tr>
		<tr>
			<th>商品名</th>
			<td><%= pb.getPro_name() %></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td><%= pb.getCat_id() %></td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%= pb.getPro_price() %></td>
		</tr>
		<tr>
			<th>在庫</th>
			<td><%= pb.getStock_no() %></td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><%= pb.getPro_msg() %></td>
		</tr>
	</table>
	<form action="http://localhost:8080/ECsite/ProductDetailServlet" method="POST">
		<input type="number" name="number" value="0" min="1" max="<%= pb.getStock_no() %>">
		<input type="hidden" name="pro_cd" value="<%= pb.getPro_cd() %>">
		<input type="hidden" name="pro_name" value="<%= pb.getPro_name() %>">
		<input type="hidden" name="pro_price" value="<%= pb.getPro_price() %>">
		<input type="hidden" name="stock_no" value="<%= pb.getStock_no() %>">
		<input type="submit" value="カートへ">
	</form>
	<form action="http://localhost:8080/ECsite/CategoryServlet" method="POST">
		<input type="submit" value="もどる">
	</form>

</body>
</html>