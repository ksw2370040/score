<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <!-- スタイルシートのリンク -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
</head>
<body>
    <!-- 共通のベースJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4">
                <h2>メニュー</h2>

                <!-- 学生管理リンク -->
                <div class="sub">
                    <a href="../student/StudentList.action">学生管理</a>
                </div>

                <!-- 成績管理セクション -->
                <div class="sub2">
                    <p>成績管理</p>
                    <a href="../results/TestRegist2.action">成績登録</a><br><br>
                    <a href="../test/TestList.action">成績参照</a>
                </div>

                <!-- 科目管理リンク -->
                <div class="sub3">
                    <a href="../subject/SubjectList.action">科目管理</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
