<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <title>得点管理システム</title>
    <!-- 外部スタイルシートのリンク -->
    <link href="../css/student-add.css" rel="stylesheet" />
</head>
<body>
    <!-- 共通のベースページをインポート -->
    <c:import url="../common/base.jsp">
        <!-- ベースページにパラメータを渡す -->
        <c:param name="title"></c:param> <!-- ページタイトル -->
        <c:param name="scripts"></c:param> <!-- ページに必要なスクリプト -->
        <c:param name="content">
            <!-- 成績削除完了メッセージのセクション -->
            <section class="me-4">
                <div id="addcomplete">
                    <h2>成績管理</h2> <!-- セクションタイトル -->
                    <p>削除が完了しました</p> <!-- 成績削除完了のメッセージ -->
                </div>
                <!-- リンクコンテナ -->
                <div class="link-container">
                    <p>
                        <!-- 戻るリンクと成績参照リンク -->
                        <a href="../results/TestRegist2.action">戻る</a>
                        <a href="../results/TestList.action">成績参照</a>
                    </p>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
