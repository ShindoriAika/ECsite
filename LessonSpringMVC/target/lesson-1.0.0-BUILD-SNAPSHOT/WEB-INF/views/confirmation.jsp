<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>【ECサイト】購入確認画面</title>
</head>
<body>
<h1>購入してよろしいでしょうか？？</h1>
<form method="post" action="/lesson/search" >
 <input type="text" name="acc_cd" th:value="*{acc_cd}">
 <br/><br/>
 <input type="submit" value="いいえ">
 </form>
 <form method="post" action="/lesson/finish" >
 <input type="submit" value="はい">
 </form>
</body>
</html>