<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>科目管理</title> <!-- ページタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- CSSファイルをリンク -->
</head>
<body>
<!-- 共通の基盤となるJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4"> <!-- メインコンテンツセクション -->
                <h2>科目情報変更</h2> <!-- セクション見出し -->
                <div class="comp"> <!-- 変更完了メッセージ用のコンテナ -->
                    <p>変更が完了しました</p> <!-- 変更完了のメッセージ -->
                </div>
                <div class="comp2"> <!-- 戻るリンク用のコンテナ -->
                    <a href="../subject/SubjectList.action">科目一覧</a> <!-- 科目一覧ページへのリンク -->
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
