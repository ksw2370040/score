<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // セッションからユーザー情報を取得
    // ユーザーがログインしていない場合は、ログインアクションにリダイレクト
    if (session.getAttribute("user") == null) {
        request.getRequestDispatcher("../user/LoginExecute.action").forward(request, response);
        return; // 処理を終了
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link href="../css/stylesheet.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
    <header>
        <h1>得点管理システム</h1>
        <div class="header">
            <!-- タグライブラリを使用してセッションからユーザー名を取得 -->
            <span>${sessionScope.user.name}様</span> <!-- セッションスコープからユーザー名を取得 -->
            <a href="../user/logout.jsp">ログアウト</a> <!-- ログアウトリンク -->
        </div>
    </header>
    <div class="sidebar">
        <nav>
            <ul>
                <li><a href="../menu/menu.jsp">メニュー</a></li> <!-- メニューリンク -->
                <li><a href="../student/StudentList.action">学生管理</a></li> <!-- 学生管理リンク -->
                <li><label for="score">成績管理</label></li> <!-- 成績管理ラベル -->
                <li><a href="../results/TestRegist2.action" id="score">成績登録</a></li> <!-- 成績登録リンク -->
                <li><a href="../results/TestList.action" id="score">成績参照</a></li> <!-- 成績参照リンク -->
                <li><a href="../subject/SubjectList.action">科目管理</a></li> <!-- 科目管理リンク -->
            </ul>
        </nav>
    </div>
    <div class="content">
        <!-- リクエストパラメータからコンテンツを取得して表示 -->
        ${param.content} <!-- リクエストパラメータからコンテンツを動的に表示 -->
    </div>
    <footer>
        <p>© 2023 TIC</p> <!-- フッターの著作権表示 -->
        <p>大原学園</p> <!-- 学校名の表示 -->
    </footer>
</body>
</html>
