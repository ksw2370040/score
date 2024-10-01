<%@page contentType="text/html; charset=UTF-8"%> <!-- ページのコンテンツタイプと文字エンコーディングを指定 -->

<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title> <!-- ページタイトル -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- CSSスタイルシートをリンク -->
    <style>
        footer {
            position: fixed; /* フッターを固定位置に設定 */
            bottom: 0; /* 下端に固定 */
            height: 50px; /* 高さを設定 */
        }

        h2 {
            height: 60px; /* 高さを設定 */
            width: 50%; /* 幅を50%に設定 */
            display: flex; /* フレックスボックスを使用 */
            align-items: center; /* 垂直方向に中央揃え */
            margin-left: 25%; /* 左のマージンを25%に設定 */
            margin-bottom: 20px; /* 下のマージンを設定 */
            margin-top: 20px; /* 上のマージンを設定 */
            background-color: #dddddd; /* 背景色を設定 */
        }

        .logout {
            height: 40px; /* 高さを設定 */
            width: 50%; /* 幅を50%に設定 */
            margin-left: 25%; /* 左のマージンを25%に設定 */
            display: flex; /* フレックスボックスを使用 */
            justify-content: center; /* 水平方向に中央揃え */
            align-items: center; /* 垂直方向に中央揃え */
            background-color: #339966; /* 背景色を設定 */
        }

        .logout2 {
            width: 50%; /* 幅を50%に設定 */
            margin-left: 25%; /* 左のマージンを25%に設定 */
            margin-top: 150px; /* 上のマージンを設定 */
        }
    </style>
</head>
<body>
    <header>
        <h1>得点管理システム</h1> <!-- ヘッダーにシステム名を表示 -->
        <div class="header">
            <!-- ヘッダーに他の内容が必要な場合に備えたダミー要素 -->
        </div>
    </header>
    <h2>&ensp;ログアウト</h2> <!-- ログアウトタイトル -->
    <div class="logout">
        <p>ログアウトしました</p> <!-- ログアウトメッセージ -->
    </div>
    <div class="logout2">
        <p><a href="login.jsp">ログイン</a></p> <!-- ログインページへのリンク -->
    </div>
    <footer>
        <p>© 2023 TIC</p> <!-- 著作権表示 -->
        <p>大原学園</p> <!-- 学校名 -->
    </footer>
</body>
</html>
