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
        <c:param name="title">科目管理</c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4"> <!-- メインコンテンツセクション -->
                <h2>科目管理</h2> <!-- セクション見出し -->
                <form method="get"> <!-- 検索フォーム -->
                    <input id="subject-name" type="text" name="f1" placeholder="科目名を入力してください"> <!-- 科目名入力フィールド -->
                    <button id="filter-button">検索</button> <!-- 検索ボタン -->
                </form>
                <div class="new"> <!-- 新規登録リンクを表示するセクション -->
                    <a href="SubjectCreate.action">新規登録</a> <!-- 新しい科目の登録ページへのリンク -->
                </div>
                <table> <!-- 科目情報を表示するテーブル -->
                    <tr>
                        <th>科目コード</th> <!-- テーブルヘッダー: 科目コード -->
                        <th>科目名</th> <!-- テーブルヘッダー: 科目名 -->
                        <th></th> <!-- 空のヘッダー (変更リンク用) -->
                        <th></th> <!-- 空のヘッダー (削除リンク用) -->
                    </tr>
                    <c:forEach var="subject" items="${subjects}"> <!-- 科目情報のリストをループ -->
                        <tr>
                            <td>${subject.cd}</td> <!-- 科目コードを表示 -->
                            <td>${subject.name}</td> <!-- 科目名を表示 -->
                            <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td> <!-- 変更リンク -->
                            <td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td> <!-- 削除リンク -->
                        </tr>
                    </c:forEach>
                </table> <!-- テーブルの終了 -->
            </div>
        </section>
    </c:param>
    </c:import>
</body>
</html>
