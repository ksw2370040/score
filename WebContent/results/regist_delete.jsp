<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>
    <!-- スタイルシートのリンク -->
    <link rel="stylesheet" type="text/css" href="../css/results.css">
<style>
/* ベースのスタイル */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f9f9f9;
}

/* セクションスタイル */
section {
    max-width: 1200px;
    margin-left: 27%; /* メニューの幅+余白を設定 */
    margin-right: 1%;
}

/* タイトルスタイル */
h2 {
    font-size: 24px;
    background-color: #bbbbbb; /* 背景色を設定 */
    padding: 10px 20px; /* タイトルの周りに余白を追加 */
}

.new {
	text-align: right;
}

/* フォーム全体を囲むスタイル */
#filter {
    border: 2px solid #DDDDDD; /* 境界線のスタイルと色を指定 */
    padding: 20px; /* 内側の余白を追加 */
    margin-bottom: 20px; /* 下に余白を追加 */
    border-radius: 10px; /* 角を丸くする（オプション） */
    background-color: #f9f9f9; /* 背景色を追加（オプション） */
    display: flex; /* フレックスコンテナにする */
    align-items: center; /* 要素を中央に揃える */
    justify-content: space-between; /* 要素を均等に配置する */
}

/* フォームグループのスタイル */
.form-group {
    display: flex;
    flex-direction: column; /* ラベルとセレクトボックスを縦に配置 */
    margin-right: 20px; /* 右に余白を追加 */
}

/* 各ラベルとセレクトボックス/チェックボックスのスタイル */
.form-group label {
    margin-bottom: 5px; /* ラベルと入力要素の間に余白を追加 */
}

#filter div {
    display: flex;
    flex-direction: column; /* ラベルと入力要素を縦に配置 */
    margin-right: 20px; /* 各要素の間に余白を追加 */
}

/* ボタンのスタイル */
#filter-button {
    padding: 7px 14px; /* ボタンのパディングを設定 */
    background-color: #007BFF; /* ボタンの背景色 */
    color: white; /* ボタンの文字色 */
    border: none; /* ボタンの境界線をなくす */
    border-radius: 5px; /* ボタンの角を丸くする */
    cursor: pointer; /* ホバー時のカーソルをポインターにする */
}

#filter-button:hover {
    background-color: #0056b3; /* ホバー時の背景色 */
}

#test-f1-select, #test-f2-select, #test-f4-select {
    width: 100px; /* セレクトボックスの幅を指定 */
    height: 35px;
    border-radius: 5px; /* 角を丸くする */
    border: 1px solid #ccc; /* 境界線のスタイル */
}

#test-f3-select {
    width: 250px; /* セレクトボックスの幅を指定 */
    height: 35px;
    border-radius: 5px; /* 角を丸くする */
    border: 1px solid #ccc; /* 境界線のスタイル */
}


table {
    width: 95%;
    border-collapse: collapse; /* ボーダーの隙間をなくす */
    border-top: none; /* テーブル全体の上側のボーダーを削除 */
}

table th, table td {
    border-top: 1px solid #ddd; /* 上側のセル境界線 */
    border-bottom: 1px solid #ddd; /* 下側のセル境界線 */
    padding: 12px; /* セルの内側余白を広くする */
    text-align: left; /* テキストの左揃え */
}

table th {
    font-weight: bold; /* ヘッダーのテキストを太字に */
    border-top: none; /* ヘッダーセルの上側のボーダーを削除 */
}

table th:nth-child(1), table td:nth-child(1) {
    width: 300px;
}

table th:nth-child(2), table td:nth-child(2) {
    width: 200px;
}

table th:nth-child(3), table td:nth-child(3) {
    width: 200px;
}

table th:nth-child(4), table td:nth-child(4) {
    width: 300px;
}

table th:nth-child(5), table td:nth-child(5) {
    width: 350px;
}


</style>
</head>
<body>
    <!-- 共通のベースJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4">
                <!-- テストが存在するかどうかをチェック -->
                <c:choose>
                    <c:when test="${tests.size() >= 0}">
                        <form action="TestDeleteExecute.action" method="get">
                            <table>
                                <!-- テストのリストを表示 -->
                                <c:forEach var="test" items="${tests}">
                                    <tr>
                                        <!-- 各テストに関連する情報を隠しフィールドとして送信 -->
                                        <td><input type="hidden" name="student_no" value="${test.student.no}" required></td>
                                        <td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td>
                                        <td><input type="hidden" name="class_num" value="${test.classNum}" required></td>
                                        <td><input type="hidden" name="test_no" value="${test.no}" required></td>
                                        <td><input type="hidden" name="count" value="${tests.size()}" required></td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <p>本当に削除しますか</p>
                            <button>削除する</button>
                        </form>
                    </c:when>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
