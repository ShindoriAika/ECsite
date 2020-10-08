<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>【ECサイト】商品詳細画面</title>
</head>
<body>
<h1>商品紹介</h1>
<form method="post" action="/lesson/cart" >
 <input type="text" name="acc_cd" th:value="*{acc_cd}">
 <br/><br/>
 <input type="submit" value="カートへ">
 </form>
 <form method="post" action="/lesson/search" >
 <input type="submit" value="戻る">
 </form>
</body>
</html>