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
		<form action="/ECsite/LoginServlet" method="post">
			名前<input type="text" name="loginCd"><br>
			パスワード<input type="password" name="loginPw"><br>

			<input type="submit" value="ログイン">
		</form>

		<form action="/ECsite/view/MakeAccount.jsp" method="post">
			<input type="submit" value="アカウント作成">
		</form>
	</body>
</html>