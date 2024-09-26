<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // セッションに "user" が存在しない場合、ログインページへリダイレクトする処理
    if (session.getAttribute("user") == null) {
    	// ログイン処理へリクエストを転送
    	request.getRequestDispatcher("../user/LoginExecute.action").forward(request, response);
        return; // 処理をここで終了
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <!-- 外部CSSファイルの読み込み -->
    <link href="../css/stylesheet.css" rel="stylesheet" />
</head>
<body>
    <header>
        <h1>得点管理システム</h1>
        <div class="header">
            <!-- セッションからユーザー名を取得して表示 -->
            <span>${sessionScope.user.name}様</span>
            <!-- ログアウトリンク -->
            <a href="../user/logout.jsp">ログアウト</a>
        </div>
    </header>

    <div class="sidebar">
        <nav>
            <ul>
                <!-- メニュー項目 -->
                <li><a href="../menu/menu.jsp">メニュー</a></li>
                <li><a href="../student/StudentList.action">学生管理</a></li>
                <li><label for="score">成績管理</label></li>
                <li><a href="../results/TestRegist2.action" id="score">成績登録</a></li>
                <li><a href="../results/TestList.action" id="score">成績参照</a></li>
				<li><a href="../subject/SubjectList.action">科目管理</a></li>
			</ul>
        </nav>
    </div>

    <div class="content">
        <!-- リクエストパラメータ "content" を表示 -->
        ${param.content}
    </div>

    <footer>
        <!-- フッターに固定の情報を表示 -->
        <p>© 2023 TIC</p>
        <p>大原学園</p>
    </footer>
</body>

</html>
