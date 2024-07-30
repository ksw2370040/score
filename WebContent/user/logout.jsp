<%@page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link href="../css/stylesheet.css" rel="stylesheet" />
    <style>
    	footer{
            position: fixed;
            bottom: 0;
            height: 50px;
        }

        h2{
        	height: 60px;
        	width: 50%;
        	display: flex;
  			align-items: center;
        	margin-left: 25%;
        	margin-bottom: 20px;
        	margin-top: 20px;
        	background-color:#dddddd;
        }
        .logout{
        	height: 40px;
        	width: 50%;
        	margin-left: 25%;
        	display: flex;
  			justify-content: center;
  			align-items: center;
        	background-color:#339966;
        }
        .logout2{
        	width: 50%;
        	margin-left: 25%;
        	margin-top: 150px;
        }
    </style>
</head>
<body>
    <header>
        <h1>得点管理システム</h1>
        <div class="header">

        </div>
    </header>
    <h2>&ensp;ログアウト</h2>
    <div class= "logout">
    	<p>ログアウトしました</p>
    </div>
    <div class= "logout2">
    	<p><a href="login.jsp">ログイン</a></p>
    </div>
    <footer>
        <p>© 2023 TIC</p>
        <p>大原学園</p>
    </footer>
</body>

</html>