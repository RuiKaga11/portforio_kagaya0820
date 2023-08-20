<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg2 = (String) request.getAttribute("errorMsg2");
%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg3 = (String) request.getAttribute("errorMsg3");
%>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg4 = (String) request.getAttribute("errorMsg4");
%>

<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録ページ</title>
</head>
<body>
<h1>アカウント登録ページ</h1>
<form action="/dokoTsubu_DB/Account" method="post">
ユーザー名：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass"><br>
パスワードの再入力：<input type="password" name="pass2"><br>
<input type="submit" value="登録">
</form>
<% if(errorMsg != null){%>
<p><%= errorMsg%></p>
<%} %>
<% if(errorMsg2 != null){%>
<p><%= errorMsg2%></p>
<%} %>
<% if(errorMsg3 != null){%>
<p><%= errorMsg3%></p>
<%} %>
<% if(errorMsg4 != null){%>
<p><%= errorMsg4%></p>
<%} %>
</body>
</html>