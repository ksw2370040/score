<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/student.css"> <!-- スタイルシートのリンク -->
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
    padding: 10px 20px; /* ボタンのパディングを設定 */
    background-color: #007BFF; /* ボタンの背景色 */
    color: white; /* ボタンの文字色 */
    border: none; /* ボタンの境界線をなくす */
    border-radius: 5px; /* ボタンの角を丸くする */
    cursor: pointer; /* ホバー時のカーソルをポインターにする */
}

#filter-button:hover {
    background-color: #0056b3; /* ホバー時の背景色 */
}

#student-f1-select, #student-f2-select {
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

table th:nth-child(3), table td:nth-child(3) {
    width: 250px;
}

/* クラス列の横幅を狭くする */
table th:nth-child(4), table td:nth-child(4) {
    width: 100px;
}
.center {
    text-align: center;
}

</style>
</head>
<body>
	<!-- 共通のベースJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4"> <!-- メインコンテンツセクション -->
                <h2>学生管理</h2> <!-- セクションタイトル -->
                <div class="new">
                    <a href="StudentCreate.action">新規登録</a> <!-- 新規登録へのリンク -->
                </div>
                <form method="get"> <!-- フィルタリング用のフォーム -->
                    <div id="filter">
                        <div class="form-group">
                            <label for="student-f1-select">入学年度</label>
                            <select id="student-f1-select" name="f1"> <!-- 入学年度の選択 -->
                                <option value="0">--------</option> <!-- デフォルトオプション -->
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year}</option> <!-- 年度のオプションを動的に生成 -->
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f2-select">クラス</label>
                            <select id="student-f2-select" name="f2"> <!-- クラスの選択 -->
                                <option value="0">--------</option> <!-- デフォルトオプション -->
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num==f2 }">selected</c:if>>${num}</option> <!-- クラス番号のオプションを動的に生成 -->
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f3-check">在学中
                                <input type="checkbox" id="student-f3-check" name="f3" value="t" <c:if test="${!empty f3}">checked</c:if>> <!-- 在学中のチェックボックス -->
                            </label>
                        </div>
                        <div>
                            <button id="filter-button">絞込み</button> <!-- 絞込みボタン -->
                        </div>
                        <div style="color:red; ">${errors.get("f1")}</div> <!-- エラーメッセージ -->
                    </div>
                </form>

                <c:choose>
                    <c:when test="${students.size()>0}"> <!-- 学生情報が存在する場合 -->
                        <div>検索結果:${students.size()}件</div> <!-- 検索結果件数表示 -->
                        <table>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th class="center">在学中</th>
                                <th></th>
                            </tr>
                            <c:forEach var="student" items="${students}"> <!-- 学生情報のリストを表示 -->
                                <tr>
                                    <td>${student.entYear}</td> <!-- 入学年度 -->
                                    <td>${student.no}</td> <!-- 学生番号 -->
                                    <td>${student.name}</td> <!-- 氏名 -->
                                    <td>${student.classNum}</td> <!-- クラス番号 -->
                                    <td class="center">
                                        <c:choose>
                                            <c:when test="${student.isAttend()}"> <!-- 在学中かどうかをチェック -->
                                                〇
                                            </c:when>
                                            <c:otherwise>
                                                ×
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td> <!-- 学生情報変更へのリンク -->
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>学生情報が存在しませんでした</div> <!-- 学生情報が存在しない場合 -->
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
