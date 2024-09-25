<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %> <!-- java.utilパッケージをインポート -->

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
	<title>削除確認</title> <!-- ページタイトル -->
	<link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- スタイルシートをリンク -->
</head>
<body>
<c:import url="../common/base.jsp"> <!-- 共通のベースページをインポート -->
	<c:param name="title"></c:param> <!-- ページタイトルを設定（空） -->
	<c:param name="scripts"></c:param> <!-- スクリプトを設定（空） -->
	<c:param name="content"> <!-- コンテンツ部分を定義 -->
	<section class="me-4"> <!-- セクションを作成 -->
		<h2>科目削除確認</h2> <!-- 見出し -->
		<p>「${subject.name}( ${subject.cd})」を削除してもよろしいですか</p> <!-- 削除する科目名とコードを表示 -->
		<a class="delete-button" href="#" onclick="deleteSubject('${subject.cd}')">削除</a><br><br><br><br> <!-- 削除ボタン -->
		<a href="../subject/SubjectList.action">戻る</a> <!-- 科目一覧へのリンク -->
	</section>
	</c:param>
</c:import>

<style>
    .delete-button { /* 削除ボタンのスタイル */
        display: inline-block; /* インラインブロック要素にする */
        padding: 5px 10px; /* 内側の余白 */
        margin: 5px 0; /* 外側の余白 */
        font-size: 14px; /* フォントサイズ */
        font-weight: bold; /* フォントを太字に */
        color: #fff; /* 文字色を白に */
        background-color: #d9534f; /* 背景色を赤に */
        border: none; /* ボーダーなし */
        border-radius: 4px; /* 角を丸く */
        text-decoration: none; /* 下線を削除 */
        text-align: center; /* テキストを中央揃え */
    }

    .delete-button:hover { /* ホバー時のスタイル */
        background-color: #c9302c; /* 背景色を少し暗い赤に */
    }
</style>

<script type="text/javascript">
function deleteSubject(subjectCd) { // 科目を削除する関数
    // XMLHttpRequestを使用して削除リクエストを送信
    var xhr = new XMLHttpRequest(); // 新しいXMLHttpRequestオブジェクトを作成
    xhr.open("GET", "SubjectDeleteExecute.action?cd=" + subjectCd, true); // リクエストの初期化
    xhr.onreadystatechange = function () { // リクエストの状態が変わるたびに実行される関数
        if (xhr.readyState == 4 && xhr.status == 200) { // リクエストが完了し、成功した場合
            // リクエストが成功した場合、削除完了画面にリダイレクト
            window.location.href = "deleteSuccess.jsp"; // 削除成功画面へ遷移
        }
    };
    xhr.send(); // リクエストを送信
}
</script>
</body>
</html>
