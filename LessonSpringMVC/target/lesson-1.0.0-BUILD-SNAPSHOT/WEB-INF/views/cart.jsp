<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>【ECサイト】カート画面</title>
</head>
<body>
<h1>カート</h1>
<form method="post" action="/lesson/search" >
 <input type="text" name="acc_cd" th:value="*{acc_cd}">
 <br/><br/>
 <input type="submit" value="買い物を続ける">
 </form>
 <form method="post" action="/lesson/confirmation" >
 <input type="submit" value="購入">
 </form>
</body>
</html>