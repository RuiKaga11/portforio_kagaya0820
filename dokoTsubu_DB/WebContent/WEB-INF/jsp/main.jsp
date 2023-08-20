<%@page import="model.Mutter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<%
//アプリケーションスコープに保存されたつぶやきリストを取得
List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
%>
<%
//リクエストスコープに保存された検索用つぶやきリストを取得
List<Mutter> sortResult = (List<Mutter>) request.getAttribute("sortResult");
%>
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
//リクエストスコープに保存された登録成功メッセージを取得
String trueMsg2 = (String) request.getAttribute("trueMsg2");
%>
<%
//リクエストスコープに保存された登録成功メッセージを取得
String deleteTrueMsg = (String) request.getAttribute("deleteTrueMsg");
String deleteFalseMsg = (String) request.getAttribute("deleteFalseMsg");
String deleteFalseMsg2 = (String) request.getAttribute("deleteFalseMsg2");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<%= loginUser.getName()%>さん、ログイン中
<a href="/dokoTsubu_DB/Logout">ログアウト</a>
</p>
<form action="/dokoTsubu_DB/Sort" method="post">
<input type="text" name="sortText">
<input type="submit" value="検索">
</form>

<form action="/dokoTsubu_DB/Delete" method="post">
<p>削除には検索後IDを入力してください。</p>
<input type="text" name="deleteId">
<input type="submit" value="一致と同時に削除">
</form>
<% if(deleteTrueMsg != null){%>
<p><%= deleteTrueMsg%></p>
<%}; %>
<% if(deleteFalseMsg != null){%>
<p><%= deleteFalseMsg%></p>
<%}; %>
<% if(deleteFalseMsg2 != null){%>
<p><%= deleteFalseMsg2%></p>
<%}; %>
<% if(sortResult != null){%>
<% for(Mutter sort : sortResult) {%>
<p><%= sort.getId()%>:<%= sort.getUserName()%>:<%= sort.getText()%>
</p>
<% };%>
<% }; %>

<p><a href="/dokoTsubu_DB/Main">更新</a></p>
<form action="/dokoTsubu_DB/Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>

<% if(errorMsg != null){%>
<p><%= errorMsg%></p>
<%}; %>
<% if(errorMsg2 != null){%>
<p><%= errorMsg2%></p>
<%}; %>
<% if(errorMsg3 != null){%>
<p><%= errorMsg3%></p>
<%}; %>
<% if(trueMsg2 != null){%>
<p><%= errorMsg%></p>
<%}; %>
<% for(Mutter mutter : mutterList) {%>
<p><%= mutter.getUserName()%>:<%= mutter.getText()%></p>
<% };%>
</body>
</html>