<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント作成画面</title>
</head>
<body>
	<h1>アカウント作成</h1>
	<p>${message}</p>
	<form action="/MakeAccountServlet" method="post">
		名前<input type="text" name="loginCd"><br>
		パスワード<input type="password" name="loginPw"><br>

		<input type="submit" value="登録">
	</form>

	<form action="/view/Login.jsp" method="post">
		<input type="submit" value="もどる">
	</form>
</body>
</html>