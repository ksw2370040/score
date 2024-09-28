<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>科目管理</title> <!-- ページタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- CSSファイルをリンク -->
    <style>
		section {
		    max-width: 1200px;
		    margin-left: 27%; /* メニューの幅+余白を設定 */
		    margin-right: 1%;
		}

		h2{
			font-size: 24px;
		    background-color: #bbbbbb; /* 背景色を設定 */
		    padding: 10px 20px; /* タイトルの周りに余白を追加 */
		}

		.new {
			text-align: right;
		}

		#subject-name {
		    width: 220px; /* 入力フィールドの幅を指定 */
		    height: 23px; /* 入力フィールドの高さを指定 */
		    border-radius: 5px; /* 角を丸くする */
		    border: 1px solid #ccc; /* 境界線のスタイル */
		    padding: 5px 10px; /* 内側の余白 */
		    font-size: 16px; /* フォントサイズ */
		    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 影を追加（オプション） */
		    margin-right: 10px; /* ボタンとの間に余白を追加 */
		}

		/* ボタンのスタイル */
		#filter-button {
		    padding: 5px 10px; /* ボタンのパディングを設定 */
		    background-color: #007BFF; /* ボタンの背景色 */
		    color: white; /* ボタンの文字色 */
		    border: none; /* ボタンの境界線をなくす */
		    border-radius: 5px; /* ボタンの角を丸くする */
		    cursor: pointer; /* ホバー時のカーソルをポインターにする */
		    font-size: 16px; /* フォントサイズ */
		    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 影を追加（オプション） */
		}

		#filter-button:hover {
		    background-color: #0056b3; /* ホバー時の背景色 */
		}

		#filter div {
		    margin-bottom: 15px; /* 各要素の下に余白を追加 */
		}

		#filter label {
		    display: block; /* ラベルをブロック要素にすることで上に配置 */
		    margin-bottom: 5px; /* ラベルとフォーム要素の間に余白を追加 */
		}

		#filter input[type="text"],
		#filter select {
		    padding: 8px; /* フォーム要素のパディングを追加 */
		    border: 1px solid #ccc; /* 枠線を追加 */
		    border-radius: 4px; /* 角丸にする */
		    width: 850px; /* 幅を適宜調整 */
		    box-sizing: border-box; /* パディングとボーダーを含めたボックスサイズ */
		}

		#button{
			padding: 10px 20px; /* ボタンのパディングを設定 */
			background-color: blue; /* ボタンの背景色 */
		    color: white; /* ボタンの文字色 */
		    border: none; /* ボタンの境界線をなくす */
		    border-radius: 5px; /* ボタンの角を丸くする */
		    cursor: pointer; /* ホバー時のカーソルをポインターにする */
		}

		.comp{
			background-color: #339966;
			text-align: center;
		}

		.comp2{
			margin-top: 150px;
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

		 table th:nth-child(2), table td:nth-child(2) {
		    width: 400px;
		}

		 table th:nth-child(3), table td:nth-child(3) {
		    width: 100px;
		}

		table th:nth-child(4), table td:nth-child(4) {
		    width: 100px;
		}

	</style>

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
