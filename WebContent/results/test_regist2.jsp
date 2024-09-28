<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
	    <title>成績登録</title>
	    <link rel="stylesheet" type="text/css" href="../css/results.css"> <!-- スタイルシートの読み込み -->
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
					<h2>成績管理</h2> <!-- セクションタイトル -->

					<!-- 成績管理用のフォーム -->
					<form method="get">
						<div id="filter">
							<!-- 入学年度の選択 -->
							<div id="form-group">
								<label for="test-f1-select">入学年度</label>
								<select id="test-f1-select" name="f1">
									<option value="0">--------</option> <!-- 初期値 -->
									<c:forEach var="year" items="${ent_year_set}"> <!-- 入学年度のオプションを動的に生成 -->
										<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
									</c:forEach>
								</select>
							</div>

							<!-- クラスの選択 -->
							<div id="form-group">
								<label for="test-f2-select">クラス</label>
								<select id="test-f2-select" name="f2">
									<option value="0">--------</option> <!-- 初期値 -->
									<c:forEach var="num" items="${class_num_set}"> <!-- クラス番号のオプションを動的に生成 -->
										<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
									</c:forEach>
								</select>
							</div>

							<!-- 科目名の選択 -->
							<div id="form-group">
								<label for="test-f3-select">科目名</label>
								<select id="test-f3-select" name="f3">
									<option value="0">--------</option> <!-- 初期値 -->
									<c:forEach var="sub" items="${subjects}"> <!-- 科目名のオプションを動的に生成 -->
										<option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>${sub.name}</option>
									</c:forEach>
								</select>
							</div>

							<!-- 回数の選択 -->
							<div id="form-group">
								<label for="test-f4-select">回数</label>
								<select id="test-f4-select" name="f4">
									<option value="0">0</option> <!-- 初期値 -->
									<c:forEach var="n" items="${number}"> <!-- 回数のオプションを動的に生成 -->
										<option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
									</c:forEach>
								</select>
							</div>

							<div>
								<button id="filter-button" type="submit">検索</button> <!-- 検索ボタン -->
							</div>

							<!-- 入力エラーの表示 -->
							<div>${errors.get("f1")}</div>
						</div>
					</form>

					<!-- 検索結果がある場合の処理 -->
					<c:choose>
					    <c:when test="${tests.size() > 0}">
							<form action="TestRegist2Execute.action" method="get"> <!-- 成績登録用のフォーム -->
					            <c:set var="test" value="${tests[0]}" /> <!-- 最初のテストデータをセット -->
		    					<div>科目:${subject.name} (${test.no}回)</div> <!-- 科目名と回数の表示 -->

					            <table>
					                <tr>
					                    <th>入学年度</th>
					                    <th>クラス</th>
					                    <th>学生番号</th>
					                    <th>氏名</th>
					                    <th>点数</th>
					                </tr>
									<c:forEach var="test" items="${tests}"> <!-- テストデータの表示 -->
					                    <tr>
					                        <td>${test.student.entYear}</td> <!-- 入学年度 -->
					                        <td>${test.student.classNum}</td> <!-- クラス番号 -->
					                        <td>${test.student.no}</td> <!-- 学生番号 -->
					                        <td>${test.student.name}</td> <!-- 学生名 -->
					                        <td>
												<input id="text" type="text" name="test_point" value="${test.point}" required> <!-- 点数入力フィールド -->
											</td>
											<td><input type="hidden" name="student_no" value="${test.student.no}" required></td>
											<td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td>
											<td><input type="hidden" name="class_num" value="${test.classNum}" required></td>
											<td><input type="hidden" name="test_no" value="${test.no}" required></td>
											<td><input type="hidden" name="count" value="${tests.size()}" required></td>
										</tr>
									</c:forEach>
								</table>
								<button id="filter-button" type="submit">登録して終了</button> <!-- 成績登録ボタン -->
							</form>
					    </c:when>
					    <c:otherwise>
					        <div>テストデータが見つかりませんでした。</div> <!-- データがない場合のメッセージ -->
					    </c:otherwise>
					</c:choose><br><br>

					<!-- 成績削除用の処理 -->
					<c:choose>
					    <c:when test="${tests.size() > 0}">
							<form action="TestDelete.action" method="get"> <!-- 成績削除用のフォーム -->
					            <table class="table2">
									<c:forEach var="test" items="${tests}"> <!-- テストデータの表示 -->
					                    <tr>
											<td><input type="hidden" name="student_no" value="${test.student.no}" required></td>
											<td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td>
											<td><input type="hidden" name="class_num" value="${test.classNum}" required></td>
											<td><input type="hidden" name="test_no" value="${test.no}" required></td>
											<td><input type="hidden" name="count" value="${tests.size()}" required></td>
										</tr>
									</c:forEach>
					            </table>
					            <button id="filter-button" type="submit">削除する</button> <!-- 成績削除ボタン -->
					        </form>
					    </c:when>
					</c:choose>

				</section>
			</c:param>
		</c:import>
	</body>
</html>
