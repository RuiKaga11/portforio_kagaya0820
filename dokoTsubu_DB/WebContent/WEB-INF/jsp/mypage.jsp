<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Mutter,java.util.List,model.User,java.util.Collections" %>
<!DOCTYPE html>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<%
//リクエストスコープに保存された推奨メッセージを取得
String recomMsg = (String) request.getAttribute("recomMsg");
%>
<%
//リクエストスコープに保存された自分だけのつぶやきリストを取得
List<Mutter> mymutterList = (List<Mutter>) request.getAttribute("mymutterList");
%>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>
<p><%= loginUser.getName()%>さんのマイページ</p>
<p><a href="/dokoTsubu_DB/Main">戻る</a></p>
<% if(recomMsg != null){%>
<p><%= recomMsg%></p>
<%} %>
<% if(mymutterList != null){%>
<% Collections.reverse(mymutterList);%>
<% for(Mutter mymutter : mymutterList) {%>
<p><%= mymutter.getText()%></p>
<%}; %>
<%}; %>

</body>
</html>