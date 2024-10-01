<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <title>得点管理システム</title>
    <!-- CSSスタイルシートへのリンク -->
    <link href="../css/student-add.css" rel="stylesheet" />
</head>
<body>
    <!-- 共通のベースJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <!-- base.jspにコンテンツを設定 -->
            <section class="me-4">
                <!-- 成績管理の完了メッセージを表示するセクション -->
                <div id="addcomplete">
                    <h2>成績管理</h2>
                    <p>削除が完了しました</p> <!-- 削除完了メッセージ -->
                </div>

                <div class="link-container">
                    <!-- リンクをまとめるためのコンテナ -->
                    <p>
                        <a href="../results/TestRegist2.action">戻る</a>
                        <!-- 「戻る」リンク。成績登録ページに戻る -->
                        <a href="../results/TestList.action">成績参照</a>
                        <!-- 「成績参照」リンク。成績一覧ページに遷移 -->
                    </p>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
