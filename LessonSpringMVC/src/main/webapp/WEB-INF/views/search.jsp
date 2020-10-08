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

	 	<title>【ECサイト】商品検索画面</title>

	</head>

	 <body>
	 	<div class="container" style="margin-top:10px;">
	    <div class="jumbotron">

			<table align="right">
				<tr>
					<th>
				    	<form action="myaccount" method="POST">
				           		<button type="submit" class="btn btn-primary">マイアカウント</button>
				      	</form>
			      	</th>
			      	<th>
				      	<form action="cartShow" method="POST">
				           		<button type="submit" class="btn btn-primary">　　カート　　</button>
				      	</form>
				    </th>
				</tr>
	      	</table>

	      	<br/>

	 		<h1>検索</h1>

				<span style="color:#ff0000;">${errorMessage}</span>
	      		<hr class="my-4">

	      			<form action="searchSearch" method="POST">
		        		<div class="form-group">
							<p>
				            	<input type="text" name="product_name" class="form-control">
				          	</p>
				          	<p>
					            <label class="text-left">カテゴリ</label>
					            <select name="category_id" class="form-control">
					            <option value="1">食品</option>
								<option value="2">日用品</option>
								</select>
				          	</p>
				        </div>

					    <div class="text-center">
					      	<button type="submit" class="btn btn-primary">　　検索　　</button>
					    </div>
					</form>

					<br/>
					<br/>

					<c:if test="${not empty productList}">
						<div class="container">
						<table class="table table-striped">
							<tr>
								<th>商品名</th>
								<th>価格</th>
								<th>詳細</th>
							</tr>
							<form action="searchDetail" method="POST">
							<c:forEach var="productForm" items="${productList}">
								<tr>
									<td><c:out value="${productForm.product_name}"/></td>
									<td><c:out value="${productForm.product_price}"/></td>
									<td>
										<button type="submit" value="${productForm.product_id}" name="Details" class="btn btn-primary">　　詳細　　</button>
									</td>
								</tr>
							</c:forEach>
							</form>
						</table>
						</div>
					</c:if>
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