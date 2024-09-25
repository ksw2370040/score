<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリのインポート -->

<!DOCTYPE html>
<html lang="ja">
<head>
    <title>得点管理システム</title> <!-- ページのタイトル -->
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
</head>
<body>
    <!-- base.jspのインポート -->
    <c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->
        <c:param name="title"></c:param> <!-- タイトルパラメータ -->
        <c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
        <c:param name="content"> <!-- コンテンツパラメータの開始 -->
            <section class="me-4"> <!-- 成績管理のセクション -->
                <div id="addcomplete"> <!-- 登録完了メッセージのコンテナ -->
                    <h2>成績管理</h2> <!-- セクションの見出し -->
                    <p>登録が完了しました</p> <!-- 完了メッセージ -->
                </div>
                <div class="link-container"> <!-- リンクのコンテナ -->
                    <p>
                        <a href="../results/TestRegist2.action">戻る</a> <!-- 戻るリンク -->
                        <a href="../results/TestList.action">成績参照</a> <!-- 成績参照リンク -->
                    </p>
                </div>
            </section>
        </c:param> <!-- コンテンツパラメータの終了 -->
    </c:import> <!-- base.jspのインポート終了 -->
</body>
</html>
