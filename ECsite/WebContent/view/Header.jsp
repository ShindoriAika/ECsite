<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<header>
		<form action="http://localhost:8080/ECsite/CartViewServlet" method="POST">
			<input type="submit" value="カート">
		</form>
		<form action="http://localhost:8080/ECsite/LogoutServlet" method="POST">
			<input type="submit" value="ログアウト">
		</form>
	</header>
</body>
</html>