<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <!-- 文字エンコーディングを設定 -->
    <meta charset="UTF-8">
    <!-- ページタイトルを設定 -->
    <title>科目管理</title>
    <!-- CSSスタイルシートをリンク -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
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
                <!-- 見出し -->
                <h2>科目情報登録</h2>
                <!-- 登録完了メッセージ -->
                <div class="comp">
                    <p>登録が完了しました</p>
                </div>
                <!-- 操作後のリンク -->
                <div class="comp2">
                    <!-- 登録画面に戻るリンク -->
                    <a href="../subject/SubjectCreate.action">戻る</a>
                    <!-- 科目一覧画面へのリンク -->
                    &ensp;&ensp;&ensp;&ensp;&ensp;
                    <a href="../subject/SubjectList.action">科目一覧</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
