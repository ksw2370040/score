<%@ page isErrorPage="true" %> <!-- このページはエラーページとして設定されています -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>共通エラーページ</title> <!-- エラーページのタイトル -->
</head>
<body>
    <!-- エラー情報を表示するためのパラメータ -->
    <p>${error}</p> <!-- エラーの種類を表示（例: 404エラーなど） -->

    <!-- 例外のメッセージを表示 -->
    <p><%=exception.getMessage()%></p> <!-- 例外オブジェクトからメッセージを取得して表示 -->
</body>
</html>
