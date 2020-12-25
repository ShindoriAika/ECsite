<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<header>
		<form action="/ECsite/CartServlet" method="POST">
			<input type="submit" value="カート">
			<input type="hidden" name="flg" value="1">
		</form>
		<form action="/ECsite/LogoutServlet" method="POST">
			<input type="submit" value="ログアウト">
		</form>
	</header>
</body>
</html>