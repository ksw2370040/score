<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
    <link href="../css/stylesheet.css" rel="stylesheet" />
</head>
<body>
<header>
    <h1>得点管理システム</h1>
</header>
<div class="error-message">
    <ul class="error-list">
        <%
            // エラーメッセージリストを取得
            List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
            if (errorMessages != null) {
                // エラーメッセージが存在する場合、リスト内の各メッセージを表示
                for (String message : errorMessages) {
        %>
                <li><%= message %></li>
        <%
            }
        }
        %>
    </ul>
</div>
<footer>
    <p>© 2023 TIC</p>
    <p>大原学園</p>
</footer>
</body>
</html>
