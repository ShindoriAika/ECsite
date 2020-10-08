<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jp_co.good_works.lesson.ProductForm" %>

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

	<head>
		<!-- Required meta tags -->
		  <meta charset="utf-8">
		  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		  <!-- Bootstrap CSS -->
		  <link rel="stylesheet"
		        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
		        crossorigin="anonymous">

		 <meta charset="utf-8">

		<title>【ECサイト】商品詳細画面</title>
	</head>

	<body>
		<div class="container" style="margin-top:10px; width:700px">
		<div class="jumbotron">

		<h1>商品紹介</h1>

		<span style="color:#ff0000;">${errorMessage}</span>
		<hr class="my-4">

		    <form action="cartDisplay" method="POST">
		    	<div class="form-group">

		    		<div class="container">
		        	<table class="table table-striped">
		        			<tr>
		        				<td colspan="3" align="center">
			        				<c:forEach var="productForm" items="${productList}">
			    					<img src ="./resources/img/${productForm.product_img}" width="200" hight="170"/>
			    					</c:forEach>
		    					</td>
		        			</tr>
		        			<tr>
		        				<th>商品名</th>
		        				<c:forEach var="productForm" items="${productList}">
		        				<td><c:out value="${productForm.product_name}"/></td>
		        				</c:forEach>
		        			</tr>
		        			<tr>
		        				<th>カテゴリ</th>
		        				<c:forEach var="productForm" items="${productList}">
		        				<td><c:out value="${productForm.category_name}"/></td>
		        				</c:forEach>
		        			</tr>
		        			<tr>
		        				<th>価格</th>
		        				<c:forEach var="productForm" items="${productList}">
		        				<td><c:out value="${productForm.product_price}"/></td>
		        				</c:forEach>
		        			</tr>
		        			<tr>
		        				<th>在庫</th>
		        				<c:forEach var="productForm" items="${productList}">
		        				<td><c:out value="${productForm.stock}"/></td>
		        				</c:forEach>
		        			</tr>
		        			<tr>
		        				<th>商品紹介</th>
		        				<c:forEach var="productForm" items="${productList}">
		        				<td><c:out value="${productForm.product_detail}"/></td>
		        				</c:forEach>
		        			</tr>
		        	</table>
		        	</div>

		        	</br>

				           	<label class="text-left">個数</label>
					           	<c:forEach var="productForm" items="${productList}">
					           	<input type="number" name="number" value="0" min="1" max="${productForm.stock}" class="form-group">
					           	</c:forEach>

			       <table align="right">
			          	<tr>
		           			<th>
		        				<c:forEach var="productForm" items="${productList}">
		        				<button type="submit" value="${productForm.product_id}" name="CproductId" class="btn btn-primary">　カートへ　</button>
		        				</c:forEach>
		        			</th>
					</div>
				</form>
							<th>
							    <form action="search" method="POST">
							      	<div class="text-right">
							           	<button type="submit" class="btn btn-primary">　　戻る　　</button>
							        </div>
							    </form>
						    </th>
						</tr>
					</table>

	</div>
	</div>
	 <!-- Optional JavaScript -->
	  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	          integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	          crossorigin="anonymous"></script>

	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	          integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	          crossorigin="anonymous"></script>

	  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	          integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	          crossorigin="anonymous"></script>

	 </body>

</html>