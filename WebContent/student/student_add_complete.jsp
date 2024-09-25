<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリのインポート -->
<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title> <!-- ページのタイトル -->
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
<c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->
    <c:param name="title"></c:param> <!-- タイトルパラメータ -->
    <c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
    <c:param name="content"> <!-- コンテンツパラメータの開始 -->
        <section class="me-4"> <!-- 学生情報登録完了のセクション -->
            <div id="addcomplete"> <!-- 登録完了メッセージを表示するDIV -->
                <h2>学生情報登録</h2> <!-- 見出し -->
                <p>登録が完了しました</p> <!-- 登録完了のメッセージ -->
                <div class="link-container"> <!-- リンクを表示するコンテナ -->
                    <a href="../student/StudentCreate.action">戻る</a> <!-- 学生登録画面に戻るリンク -->
                    <a href="../student/StudentList.action">学生一覧</a> <!-- 学生一覧画面へのリンク -->
                </div>
            </div>
        </section>
    </c:param> <!-- コンテンツパラメータの終了 -->
</c:import>
</body>
</html>
