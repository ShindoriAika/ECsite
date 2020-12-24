<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入完了画面</title>
	</head>
	<body>
		<h1>お買い上げありがとうございました！</h1>
		<form action="http://localhost:8080/ECsite/CategoryServlet" method="POST">
			<input type="submit" value="買い物を続ける">
		</form>
		<form action="http://localhost:8080/ECsite/LogoutServlet" method="POST">
			<input type="submit" value="ログアウト">
		</form>
	</body>
</html>