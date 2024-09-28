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
        header {
		    background-color: #D9E5FF;
		    width: 100%;
		    padding: 10px 0;
		    margin: 0 auto;
	    }


		header .header {
		    margin-right: 10px;
		    margin-bottom: 20px;
		    width: 100%;
		     text-align: right;
		}

		header h1 {
		    margin-left: 10px;
		    text-align: left;
		}

		}
		.header span,
		.header a {
		    margin-left: 10px;
		     text-align: right;
		}
		body{
			margin:0;
			 align-items: center;
		}
		footer {
		  left: 0;
		  bottom: 0;
		  width: 100%;
		  	display: inline-block;
			position: relative;

		  background: #dcdcdc;
		  margin:0;
		  	padding: 0;

		}
		footer p{
		  display: flex;
		  justify-content: center;
		    margin:5px;
		}
		.sidebar{
			float:left;
			margin-right:10%;
			display: inline-block;
			position: relative;
			line-height:200%;
		    margin-left: 9%;
		    margin-right: 60px;
		    height:100vh;
		    padding-right: 10%;
		    border-right: 2px solid #DDDDDD;
		    text-align: left;

		}
		.sidebar ul{
			padding-left:0;


		}
		.sidebar li {
		    list-style: none;

		}


		#score{
			margin-left:10px;
		}
		#title{

		}
		<<<<<<< HEAD
		.box {
		    border: #dddddd 1px solid; /* 境界線の指定 */
		    padding: 10px;
		    width: 300%; /* 幅を50%に設定 */
		    box-sizing: border-box;
		    display: flex;
		    flex-direction: column;
		    align-items: center;

		}



		.box-container {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    width: 100%;
		    flex-grow: 1;
		}

		.login-box {
		    border: 1px solid #dddddd;
		    padding: 20px;
		    width: 500px;
		    height: 400px;
		    box-sizing: border-box;
		    background-color: #f9f9f9;
		    text-align: center;
		}

		.login-box h2 {
		    background-color: #dddddd;
		    margin: -20px -20px 20px -20px;
		    padding: 15px;
		    width: calc(100% + 40px);
		    box-sizing: border-box;
		    text-align: center;
		}


		.sub {
			width: 22%;
			height: 20%;
			margin: 10px;
			line-height: 7;
		    text-align: center;
			background-color: #FF97C2;
			border-radius: 10px;
			position: absolute;
			top: 240px;
			left: 320px;
		}

		.sub2 {
			width: 22%;
			height: 20%;
			margin: 10px 0;
			line-height: 1.3;
			text-align: center;
			background-color: #93FFAB;
			border-radius: 10px;
			position: absolute;
			top: 240px;
			left: 630px;
			line-height: 90%;

		}

		.sub3 {
			width: 22%;
			height: 20%;
			line-height: 7;
			margin: 10px;
			text-align: center;
			background-color: #C299FF;
			border-radius: 10px;
			position: absolute;
			top: 240px;
			left: 920px;
		}
		h2 {
		    font-size: 24px;
		    background-color: #bbbbbb; /* 背景色を設定 */

		    padding: 10px 20px; /* タイトルの周りに余白を追加 */

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
