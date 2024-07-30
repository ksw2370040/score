<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>得点管理システム</title>
    <!-- CSSスタイルシートをリンク -->
    <link href="../css/student-add.css" rel="stylesheet" />
</head>
<body>
    <!-- 共通のベースレイアウトをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param> <!-- ページタイトルを設定 -->
        <c:param name="scripts"></c:param> <!-- 必要なスクリプトを設定 -->
        <c:param name="content">
            <section class="me-4">
                <div id="addcomplete">
                    <h2>学生情報変更</h2>
                    <!-- 変更完了メッセージ -->
                    <p>変更が完了しました</p>
                    <!-- 学生一覧へのリンク -->
                    <a href="../student/StudentList.action">学生一覧</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
