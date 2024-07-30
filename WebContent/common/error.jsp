<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>共通エラーページ</title>
</head>
<body>
    <!-- エラーオブジェクトを表示 -->
    <p>${error}</p>
    <!-- 例外のメッセージを表示 -->
    <p><%=exception.getMessage()%></p>
</body>
</html>
