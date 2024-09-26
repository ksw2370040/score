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
 <!-- 共通のベースJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4"> <!-- コンテンツセクション -->
                <h2>科目情報登録</h2> <!-- セクション見出し -->
                <div class="comp"> <!-- 完了メッセージを表示 -->
                    <p>登録が完了しました</p> <!-- 登録完了メッセージ -->
                </div>
                <div class="comp2"> <!-- リンクのためのコンテナ -->
                    <a href="../subject/SubjectCreate.action">戻る</a> <!-- 新規登録ページへのリンク -->
                    &ensp;&ensp;&ensp;&ensp;&ensp; <!-- スペースの挿入 -->
                    <a href="../subject/SubjectList.action">科目一覧</a> <!-- 科目一覧ページへのリンク -->
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
