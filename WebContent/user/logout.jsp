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
