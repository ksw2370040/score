<%@ page contentType="text/html; charset=UTF-8" %> <!-- ページのコンテンツタイプと文字エンコーディングを設定 -->
<%@ page import="java.util.List" %> <!-- Listクラスをインポート -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
    <title>エラー</title> <!-- ページタイトル -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- スタイルシートをリンク -->
</head>
<body>
<header>
    <h1>得点管理システム</h1> <!-- ヘッダーの見出し -->
</header>
<div class="error-message"> <!-- エラーメッセージを表示するためのdiv -->
    <ul class="error-list"> <!-- エラーメッセージリスト -->
        <%
            // エラーメッセージリストを取得
            List<String> errorMessages = (List<String>) request.getAttribute("errorMessages"); // リクエスト属性からエラーメッセージリストを取得
            if (errorMessages != null) { // エラーメッセージが存在するか確認
                // エラーメッセージが存在する場合、リスト内の各メッセージを表示
                for (String message : errorMessages) { // 各エラーメッセージに対してループ
        %>
                <li><%= message %></li> <!-- エラーメッセージをリストアイテムとして表示 -->
        <%
            }
        }
        %>
    </ul>
</div>
<footer>
    <p>© 2023 TIC</p> <!-- フッターに著作権情報 -->
    <p>大原学園</p> <!-- フッターに学校名 -->
</footer>
</body>
</html>
