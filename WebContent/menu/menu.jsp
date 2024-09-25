<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリのインポート -->

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メニュー</title> <!-- ページのタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- スタイルシートのリンク -->
</head>
<body>
    <!-- base.jspのインポート -->
    <c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->
        <c:param name="title"></c:param> <!-- タイトルパラメータ -->
        <c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
        <c:param name="content"> <!-- コンテンツパラメータの開始 -->
            <section class="me-4"> <!-- メニューセクション -->

                <h2>メニュー</h2> <!-- メニューの見出し -->

                <div class="sub">
                    <a href="../student/StudentList.action">学生管理</a> <!-- 学生管理へのリンク -->
                </div>

                <div class="sub2">
                    <p>成績管理</p> <!-- 成績管理の見出し -->
                    <a href="../results/TestRegist2.action">成績登録</a><br><br> <!-- 成績登録へのリンク -->
                    <a href="../test/TestList.action">成績参照</a> <!-- 成績参照へのリンク -->
                </div>

                <div class="sub3">
                    <a href="../subject/SubjectList.action">科目管理</a> <!-- 科目管理へのリンク -->
                </div>

            </section>
        </c:param> <!-- コンテンツパラメータの終了 -->
    </c:import> <!-- base.jspのインポート終了 -->
</body>
</html>
