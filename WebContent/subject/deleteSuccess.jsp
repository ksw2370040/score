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
        <!-- ページタイトルの設定 -->
        <c:param name="title"></c:param>
        <!-- 必要なスクリプトの設定 -->
        <c:param name="scripts"></c:param>
        <!-- ページコンテンツの設定 -->
        <c:param name="content">
            <section class="me-4">
                <!-- 削除完了メッセージ -->
                <div id="addcomplete">
                    <h2>学生情報削除</h2>
                    <!-- 削除が完了したことをユーザーに通知 -->
                    <p>削除が完了しました</p>
                    <!-- 科目一覧へのリンク -->
                    <a href="../subject/SubjectList.action">科目一覧</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
