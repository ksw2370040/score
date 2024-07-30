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
        <c:param name="title">科目管理</c:param>
        <!-- 必要なスクリプトの設定 -->
        <c:param name="scripts"></c:param>
        <!-- ページコンテンツの設定 -->
        <c:param name="content">
            <section class="me-4">
                <!-- 見出し -->
                <h2>科目管理</h2>
                <!-- 科目名で検索するためのフォーム -->
                <form method="get">
                    <input id="subject-name" type="text" name="f1" placeholder="科目名を入力してください">
                    <button id="filter-button">検索</button>
                </form>
                <!-- 新規登録リンク -->
                <div class="new">
                    <a href="SubjectCreate.action">新規登録</a>
                </div>
                <!-- 科目一覧テーブル -->
                <table>
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <!-- 科目情報を表示 -->
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>
                            <!-- 変更リンク -->
                            <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
                            <!-- 削除リンク -->
                            <td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </c:param>
    </c:import>
</body>
</html>
