<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
<!-- 共通のベースJSPをインポート -->
<c:import url="../common/base.jsp">
    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4"> <!-- 内容セクション -->
            <div id="addcomplete">
                <h2>学生情報登録</h2> <!-- セクションタイトル -->
                <p>登録が完了しました</p> <!-- 登録完了メッセージ -->
                <div class="link-container">
                    <a href="../student/StudentCreate.action">戻る</a> <!-- 戻るリンク -->
                    <a href="../student/StudentList.action">学生一覧</a> <!-- 学生一覧リンク -->
                </div>
            </div>
        </section>
    </c:param>
</c:import>
</body>
</html>
