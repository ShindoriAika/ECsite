<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp" xmlns:th="http://www.thymeleaf.org">
 <head>
 <meta charset="utf-8">
 <title>【ECサイト】商品検索画面</title>
 </head>
 <body>
 <h1>検索</h1>
<form method="post" action="/lesson/detail" th:object="${loginForm}">
<input type="text" name="acc_cd" th:value="*{acc_cd}">
<input type="submit" value="検索">
</form>
 </body>
</html>