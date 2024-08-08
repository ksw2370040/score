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
<p>${error}</p>
<p><%=exception.getMessage()%></p>
</body>
</html>
