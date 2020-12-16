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
			名前<input id="login_cd" type="text" name="login_cd"><br>
			パスワード<input id="login_pw" type="password" name="login_pw"><br>

			<input type="submit" value="ログイン">
		</form>
	</body>
</html>