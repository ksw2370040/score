<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>得点管理システム</title> <!-- ページのタイトル -->
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
<!-- 共通のベースJSPをインポート -->
<c:import url="../common/base.jsp">
    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4"> <!-- メインコンテンツセクション -->
            <div id="addcomplete"> <!-- 完了メッセージを表示するDIV -->
                <h2>学生情報変更</h2> <!-- セクションタイトル -->
                <p>変更が完了しました</p> <!-- 変更完了メッセージ -->
                <a href="../student/StudentList.action">学生一覧</a> <!-- 学生一覧へのリンク -->
            </div>
        </section>
    </c:param>
</c:import>
</body>
</html>
