<%@ page contentType="text/html; charset=UTF-8" %> <!-- ページのコンテンツタイプと文字エンコーディングを指定 -->
<%@ page import="java.util.List" %> <!-- Listクラスをインポート -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>エラー</title> <!-- ページタイトル -->
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- CSSスタイルシートをリンク -->
<style>
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
