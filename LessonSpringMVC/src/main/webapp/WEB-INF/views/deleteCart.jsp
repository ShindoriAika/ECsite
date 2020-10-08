<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jp_co.good_works.lesson.CartForm" %>

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

		<meta charset="UTF-8">

		<title>【ECサイト】カート画面</title>
	</head>

	<body>

		<div class="container" style="margin-top:10px; width:700px">
		<div class="jumbotron">

			<h1>カート</h1>

			<hr class="my-4">
			<div class="form-group">

		    	<div class="container">
		        	<table class="table table-striped">
		        		<tr>
		        			<th>商品名</th>
		        			<th>単価</th>
		       				<th>数量</th>
		       				<th>削除</th>
		       			</tr>
		       			<c:forEach var="cartForm" items="${cartList}">
		       			<tr>
		       				<td><c:out value="${cartForm.product_name}"/></td>
		       				<td><c:out value="${cartForm.product_price}"/></td>
		       				<td><c:out value="${cartForm.number}"/></td>
		       				<td>
		       					<form action="cartDelete" method="POST">
		       						<button type="submit" value="${cartForm.order_id}" name="cartDelete" class="btn btn-primary">削除</button>
		       					</form>
		       				</td>
	      				</tr>
	        			</c:forEach>
		       			<tr>
		       				<th colspan="3">消費税</th>
		       				<td><c:out value="${tax}"/></td>
	        			</tr>
	        			<tr>
		        			<th colspan="3">合計金額</th>
		       				<td><c:out value="${whole_amount}"/></td>
		       			</tr>
		        	</table>
		        </div>

				</div>

		        <br/>

		    	<form action="search" method="POST">
		    		<div style="text-align: center;">
		           	<button type="submit" class="btn btn-primary">　　戻る　　</button>
		           	</div>
		        </form>

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