<%@page contentType="text/html; charset=UTF-8"%> <!-- ページのコンテンツタイプと文字エンコーディングを設定 -->

<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title> <!-- ページタイトルを設定 -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- スタイルシートをリンク -->
    <style>
        footer {
            position: fixed; /* フッターを固定位置に設定 */
            bottom: 0; /* 画面の下部に配置 */
            height: 50px; /* フッターの高さを設定 */
        }

        h2 {
            height: 60px; /* 見出しの高さを設定 */
            width: 50%; /* 見出しの幅を設定 */
            display: flex; /* フレックスボックスを使用して整列 */
            align-items: center; /* 垂直方向の中央揃え */
            margin-left: 25%; /* 左マージンを設定 */
            margin-bottom: 20px; /* 下マージンを設定 */
            margin-top: 20px; /* 上マージンを設定 */
            background-color: #dddddd; /* 背景色を設定 */
        }

        .logout {
            height: 40px; /* ログアウトメッセージの高さを設定 */
            width: 50%; /* ログアウトメッセージの幅を設定 */
            margin-left: 25%; /* 左マージンを設定 */
            display: flex; /* フレックスボックスを使用して整列 */
            justify-content: center; /* 水平方向の中央揃え */
            align-items: center; /* 垂直方向の中央揃え */
            background-color: #339966; /* 背景色を設定 */
        }

        .logout2 {
            width: 50%; /* ログインリンクの幅を設定 */
            margin-left: 25%; /* 左マージンを設定 */
            margin-top: 150px; /* 上マージンを設定 */
        }
    </style>
</head>
<body>
    <header>
        <h1>得点管理システム</h1> <!-- ヘッダーにシステム名を表示 -->
        <div class="header"> <!-- ヘッダー用の追加DIV（現在は空） -->
        </div>
    </header>
    <h2>&ensp;ログアウト</h2> <!-- ログアウト見出し -->
    <div class="logout"> <!-- ログアウトメッセージのコンテナ -->
        <p>ログアウトしました</p> <!-- ログアウトメッセージ -->
    </div>
    <div class="logout2"> <!-- ログインリンク用のコンテナ -->
        <p><a href="login.jsp">ログイン</a></p> <!-- ログインページへのリンク -->
    </div>
    <footer>
        <p>© 2023 TIC</p> <!-- フッターに著作権情報 -->
        <p>大原学園</p> <!-- フッターに学校名 -->
    </footer>
</body>
</html>
