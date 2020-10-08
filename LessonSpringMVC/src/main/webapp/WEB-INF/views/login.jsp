<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

		<title>【ECサイト】ログイン画面</title>
	</head>

	<body>
		<div class="container" style="margin-top:10px;">
	    <div class="jumbotron">

	    	<h2>ログイン</h2>

	    		<span style="color:#ff0000;">${errorMessage}</span>
	    		<hr class="my-4">

	    		<form action="login" method="POST">
		        	<div class="form-group">
		          		<p>
		            		<label class="text-left">ユーザーID：</label>
		            		<input type="text" name="acc_cd" class="form-control"/>
		          		</p>
		          		<p>
		            		<label class="text-left">パスワード：</label>
		            		<input type="password" name="login_pw" class="form-control"/>
		          		</p>
		          	</div>

				<br/>

	          		<table align="center">
	          			<th>
	            			<button type="submit" class="btn btn-primary">　　ログイン　　</button>
	            		</th>
	      		</form>

				     	<th>
					    	<form action="account" method="POST">
						    	<button type="submit" class="btn btn-primary">アカウント作成</button>
					     	</form>
				     	</th>
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