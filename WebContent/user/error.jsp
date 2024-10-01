<%@ page contentType="text/html; charset=UTF-8" %> <!-- ページのコンテンツタイプと文字エンコーディングを指定 -->
<%@ page import="java.util.List" %> <!-- Listクラスをインポート -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>エラー</title> <!-- ページタイトル -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- CSSスタイルシートをリンク -->
</head>
<body>
<header>
    <h1>得点管理システム</h1> <!-- ヘッダーにシステム名を表示 -->
</header>

<div class="error-message"> <!-- エラーメッセージを表示するセクション -->
    <ul class="error-list"> <!-- エラーメッセージのリスト -->
        <%
            // リクエストからエラーメッセージのリストを取得
            List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
            if (errorMessages != null) { // エラーメッセージが存在する場合
                for (String message : errorMessages) { // 各エラーメッセージをループ
        %>
                    <li><%= message %></li> <!-- エラーメッセージをリストアイテムとして表示 -->
        <%
                }
            }
        %>
    </ul>
</div>

<footer> <!-- フッターセクション -->
    <p>© 2023 TIC</p> <!-- 著作権表示 -->
    <p>大原学園</p> <!-- 学校名 -->
</footer>
</body>
</html>
