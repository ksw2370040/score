<%@ page contentType="text/html; charset=UTF-8" %> <!-- ページのコンテンツタイプと文字エンコーディングを設定 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
    <title>得点管理システム</title> <!-- ページタイトル -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- スタイルシートをリンク -->
    <style>
        /* フォームグループのスタイル設定 */
        .form-group {
            position: relative; /* ラベルを相対的に配置 */
            margin-bottom: 20px; /* 各フォームグループの間にスペースを作成 */
        }
        .form-group input {
            width: 100%; /* 入力フィールドの幅を100%に設定 */
            height: 60px; /* 入力フィールドの高さを設定 */
            padding: 20px 10px 10px 10px; /* 内側の余白を設定 */
            box-sizing: border-box; /* ボックスモデルのサイズ計算を設定 */
            border-radius: 10px; /* 角を丸くする */
            border: 1px solid #ccc; /* 枠線の色を設定 */
        }
        .form-group label {
            position: absolute; /* ラベルを絶対的に配置 */
            top: 0; /* 上端を0に設定 */
            left: 10px; /* 左端を10pxに設定 */
            padding: 10px; /* 内側の余白を設定 */
            pointer-events: none; /* ラベルをクリック不可に設定 */
            color: #aaa; /* ラベルの初期色を設定 */
            font-size: 12px; /* フォントサイズを設定 */
            transition: 0.2s; /* トランジション効果を設定 */
        }
        .form-group input:focus + label,
        .form-group input:not(:placeholder-shown) + label {
            top: -10px; /* 入力フィールドがフォーカスされたとき、または内容が表示されているときにラベルを上に移動 */
            left: 10px; /* ラベルの左位置を設定 */
            font-size: 10px; /* ラベルのフォントサイズを小さくする */
            color: #333; /* ラベルの色を変更 */
        }
        footer {
            position: fixed; /* フッターを固定位置に設定 */
            bottom: 0; /* 画面の下部に配置 */
            height: 50px; /* フッターの高さを設定 */
        }
        .error-message {
            color: red; /* エラーメッセージの色を赤に設定 */
            font-size: 12px; /* フォントサイズを設定 */
            margin-bottom: 10px; /* エラーメッセージの下に余白を追加 */
        }
    </style>
</head>
<body>
<header>
    <h1>得点管理システム</h1> <!-- ヘッダーにシステム名を表示 -->
</header>
<div class="box-container"> <!-- ログインボックスのコンテナ -->
    <div class="login-box"> <!-- ログインボックス -->
        <h2>ログイン</h2> <!-- ログインセクションの見出し -->
        <form action="../user/LoginExecute.action" method="post"> <!-- フォームの送信先を設定 -->
            <%
                // リクエスト属性からログインエラーメッセージを取得し、表示する
                String loginError = (String) request.getAttribute("loginError"); // ログインエラーメッセージを取得
                if (loginError != null) { // エラーメッセージが存在する場合
            %>
                <div class="error-message"><%= loginError %></div> <!-- エラーメッセージを表示 -->
            <% } %>
            <div class="form-group"> <!-- ID入力用のフォームグループ -->
                <input type="text" id="id" name="id" maxlength="20" pattern="[a-zA-Z0-9]+" required placeholder="半角でご入力ください"> <!-- ID入力フィールド -->
                <label for="id">ID</label> <!-- IDラベル -->
            </div>
            <div class="form-group"> <!-- パスワード入力用のフォームグループ -->
                <input type="password" name="password" id="password" required placeholder="20文字以内の半角英数字でご入力ください"> <!-- パスワード入力フィールド -->
                <label for="password">パスワード</label> <!-- パスワードラベル -->
            </div>
            <p><input type="checkbox" id="showPassword" onclick="togglePasswordVisibility()"> パスワードを表示</p> <!-- パスワード表示用のチェックボックス -->
            <p><input type="submit" value="ログイン"></p> <!-- ログインボタン -->
        </form>
    </div>
</div>
<script type="text/javascript">
    function togglePasswordVisibility() { // パスワードの表示/非表示を切り替える関数
        var passwordField = document.getElementById("password"); // パスワードフィールドを取得
        var checkbox = document.getElementById("showPassword"); // チェックボックスを取得
        if (checkbox.checked) { // チェックボックスがチェックされている場合
            passwordField.type = "text"; // パスワードを表示
        } else {
            passwordField.type = "password"; // パスワードを隠す
        }
    }
</script>
<footer>
    <p>© 2023 TIC</p> <!-- フッターに著作権情報 -->
    <p>大原学園</p> <!-- フッターに学校名 -->
</footer>
</body>
</html>
