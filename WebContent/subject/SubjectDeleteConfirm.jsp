<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>削除確認</title> <!-- ページタイトル -->
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
		        <c:param name="title"></c:param>
		        <c:param name="scripts"></c:param>
		        <c:param name="content">
		            <section class="me-4"> <!-- メインコンテンツセクション -->
		                <h2>科目削除確認</h2> <!-- セクション見出し -->

		                <p>「${subject.name}( ${subject.cd})」を削除してもよろしいですか</p> <!-- 削除対象の科目名とコードを表示 -->

		                <!-- 削除ボタン。クリックでdeleteSubject関数を呼び出す -->
		                <a class="delete-button" href="#" onclick="deleteSubject('${subject.cd}')">削除</a><br><br><br><br>

		                <a href="../subject/SubjectList.action">戻る</a> <!-- 戻るリンク -->
		            </section>
		        </c:param>
		    </c:import>
		</body>

		<!-- スタイルを定義 -->
		<style>
		    .delete-button {
		        display: inline-block; /* ボタンをインラインブロックとして表示 */
		        padding: 5px 10px; /* ボタンの内側の余白 */
		        margin: 5px 0; /* ボタンの外側の余白 */
		        font-size: 14px; /* フォントサイズ */
		        font-weight: bold; /* フォントを太字に */
		        color: #fff; /* 文字色 */
		        background-color: #d9534f; /* ボタンの背景色 */
		        border: none; /* ボタンの境界線なし */
		        border-radius: 4px; /* 角を丸くする */
		        text-decoration: none; /* テキストの下線なし */
		        text-align: center; /* テキストを中央揃え */
		    }

		    .delete-button:hover {
		        background-color: #c9302c; /* ホバー時の背景色 */
		    }
</style>

<script type="text/javascript">
// 削除処理を実行する関数
function deleteSubject(subjectCd) {
    // XMLHttpRequestを使用して削除リクエストを送信
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "SubjectDeleteExecute.action?cd=" + subjectCd, true); // 削除リクエストを設定
    xhr.onreadystatechange = function () {
        // リクエストが成功した場合の処理
        if (xhr.readyState == 4 && xhr.status == 200) {
            // 削除完了画面にリダイレクト
            window.location.href = "deleteSuccess.jsp";
        }
    };
    xhr.send(); // リクエストを送信
}
</script>

</html>
