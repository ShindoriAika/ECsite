<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.CategoryBean,model.ProductBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>検索画面</title>
	</head>
	<body>
		<jsp:include page="Header.jsp"/>
		<p>${errorMessage1}</p>
		<h1>検索</h1>
		<p>${errorMessage2}</p>
		<form action="/ECsite/SearchServlet" method="post">
			<select name="catName">
				<option value="">すべて</option>
				<c:forEach var="category" items="${category}">
					<option value="${category.catId}">${category.catName}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="pageNumber" value="1">
			<input type="text" name="keyword">
			<input type="submit" value="検索">
		</form>
		<c:if test="${not empty product}">
			<table>
				<tr>
					<td>商品名</td>
					<td>価格</td>
					<td>在庫</td>
				</tr>
				<c:forEach var="product" items="${product}">
					<tr>
						<td><a href="/ECsite/SearchServlet?proCd=${product.proCd}">${product.proName}</a></td>
						<td><fmt:formatNumber value="${product.proPrice}" type="currency" maxFractionDigits="0" currencySymbol="\\" /></td>
						<c:if test="${product.stockNo != 0}">
							<td>${product.stockNo}</td>
						</c:if>
						<c:if test="${product.stockNo == 0}">
							<td>SOLD OUT</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<c:forEach begin="1" end="${count}" step="1" varStatus="status">
				<form action="/ECsite/SearchServlet" method="post">
					<input type="submit" value="${status.index}">
					<input type="hidden" name="pageNumber" value="${status.index}">
					<input type="hidden" name="catName" value="${catName}">
					<input type="hidden" name="keyword" value="${keyword}">
				</form>
			</c:forEach>
		</c:if>
	</body>
</html>