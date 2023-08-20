<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- トップのview -->
<%
//スコープに保存された入力失敗メッセージを取得
String errorMsg = (String) session.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶへようこそ</h1>
<form action="/dokoTsubu_DB/Login" method="post">
ユーザー名：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
<form action="/dokoTsubu_DB/Account" method="get">
<input type="submit" value="アカウント登録へ">
</form>
<%if(errorMsg != null) {%>
<p><%= errorMsg %></p>
<% };%>
</body>
</html>