<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CartBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入画面</title>
</head>
<body>
<% CartBean cb = new CartBean(); %>
<% ArrayList<CartBean> list = (ArrayList<CartBean>)session.getAttribute("cartList"); %>
<% int total = 0; %>
<h1>購入してよろしいでしょうか？？</h1>
	<table>
		<tr>
			<th>商品名</th>
			<th>単価</th>
			<th>数量</th>
		</tr>
		<% for(int i=0; i<list.size(); i++){
			cb = list.get(i); %>
			<tr>
				<td><%= cb.getPro_name() %></td>
				<td><%= cb.getPro_price() %></td>
				<td><%= cb.getNumber() %></td>
			</tr>
			<% total += (cb.getPro_price())*(cb.getNumber()); %>
		<% } %>
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