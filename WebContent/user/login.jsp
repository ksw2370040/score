<%@ page contentType="text/html; charset=UTF-8" %> <!-- ページのコンテンツタイプと文字エンコーディングを指定 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>得点管理システム</title> <!-- ページタイトル -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- CSSスタイルシートをリンク -->
    <style>
        /* フォームグループのスタイル */
        .form-group {
            position: relative; /* 相対位置を設定 */
            margin-bottom: 20px; /* 下のマージンを設定 */
        }
        /* 入力フィールドのスタイル */
        .form-group input {
            width: 100%; /* 幅を100%に設定 */
            height: 60px; /* 高さを設定 */
            padding: 20px 10px 10px 10px; /* 内部パディングを設定 */
            box-sizing: border-box; /* ボックスモデルを調整 */
            border-radius: 10px; /* 角を丸くする */
            border: 1px solid #ccc; /* ボーダーのスタイルを設定 */
        }
        /* ラベルのスタイル */
        .form-group label {
            position: absolute; /* 絶対位置を設定 */
            top: 0; /* 上端を0に設定 */
            left: 10px; /* 左端を10pxに設定 */
            padding: 10px; /* パディングを設定 */
            pointer-events: none; /* ポインターイベントを無効に */
            color: #aaa; /* 色を設定 */
            font-size: 12px; /* フォントサイズを設定 */
            transition: 0.2s; /* トランジション効果を設定 */
        }
        /* 入力フィールドがフォーカスまたはプレースホルダが表示されていない場合のラベルのスタイル */
        .form-group input:focus + label,
        .form-group input:not(:placeholder-shown) + label {
            top: -10px; /* 上に移動 */
            left: 10px; /* 左端を10pxに設定 */
            font-size: 10px; /* フォントサイズを小さく */
            color: #333; /* 色を濃く */
        }
        /* フッターのスタイル */
        footer {
            position: fixed; /* 固定位置 */
            bottom: 0; /* 下端に固定 */
            height: 50px; /* 高さを設定 */
        }
        /* エラーメッセージのスタイル */
        .error-message {
            color: red; /* 赤色 */
            font-size: 12px; /* フォントサイズを設定 */
            margin-bottom: 10px; /* 下のマージンを設定 */
        }
   </style>
</head>
<body>
<header>
    <h1>得点管理システム</h1> <!-- ヘッダーにシステム名を表示 -->
</header>
<div class="box-container">
    <div class="login-box">
        <h2>ログイン</h2> <!-- ログインフォームのタイトル -->
        <form action="../user/LoginExecute.action" method="post"> <!-- フォーム送信先 -->
            <%
                // リクエストからログインエラーメッセージを取得
                String loginError = (String) request.getAttribute("loginError");
                if (loginError != null) { // エラーメッセージが存在する場合
            %>
                <div class="error-message"><%= loginError %></div> <!-- エラーメッセージを表示 -->
            <% } %>
            <div class="form-group">
                <input type="text" id="id" name="id" maxlength="20" pattern="[a-zA-Z0-9]+" required placeholder="半角でご入力ください">
                <label for="id">ID</label> <!-- IDのラベル -->
            </div>
            <div class="form-group">
                <input type="password" name="password" id="password" required placeholder="20文字以内の半角英数字でご入力ください">
                <label for="password">パスワード</label> <!-- パスワードのラベル -->
            </div>
            <p><input type="checkbox" id="showPassword" onclick="togglePasswordVisibility()"> パスワードを表示</p> <!-- パスワード表示用チェックボックス -->
            <p><input type="submit" value="ログイン"></p> <!-- ログインボタン -->
        </form>
    </div>
</div>
<script type="text/javascript">
    function togglePasswordVisibility() {
        // パスワードの表示/非表示を切り替える関数
        var passwordField = document.getElementById("password");
        var checkbox = document.getElementById("showPassword");
        if (checkbox.checked) {
            passwordField.type = "text"; // チェックボックスが選択されている場合はテキストに設定
        } else {
            passwordField.type = "password"; // チェックボックスが選択されていない場合はパスワードに設定
        }
    }
</script>
<footer>
    <p>© 2023 TIC</p> <!-- 著作権表示 -->
    <p>大原学園</p> <!-- 学校名 -->
</footer>
</body>
</html>
