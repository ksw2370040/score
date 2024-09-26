<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>得点管理システム</title> <!-- ページタイトル -->
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
 <!-- 共通のベースJSPをインポート -->
<c:import url="../common/base.jsp">

    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4"> <!-- メインコンテンツセクション -->
            <div id="addcomplete"> <!-- 学生情報削除完了メッセージ用のdiv -->
                <h2>学生情報削除</h2> <!-- セクションタイトル -->
                <p>削除が完了しました</p> <!-- 削除完了メッセージ -->
                <a href="../subject/SubjectList.action">科目一覧</a> <!-- 科目一覧へのリンク -->
            </div>
        </section>
    </c:param>
</c:import>
</body>
</html>
