<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン画面</title>
	</head>
	<body>
		<h1>ログイン</h1>
		<p>${errorMessage}</p>
		<form id="form" action="http://localhost:8080/ECsite/LoginServlet" method="post">
			名前<input id="loginCd" type="text" name="loginCd"><br>
			パスワード<input id="loginPw" type="password" name="loginPw"><br>

			<input type="submit" value="ログイン">
		</form>
	</body>
</html>