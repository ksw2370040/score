<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
	    <meta charset="UTF-8">
	    <title>共通エラーページ</title>
	</head>
	<body>
	    <!-- JSTLのEL式でエラーメッセージを表示 -->
	    <p>${error}</p>

	    <!-- 例外オブジェクトから詳細なエラーメッセージを取得して表示 -->
	    <p><%= exception.getMessage() %></p>
	</body>
</html>
