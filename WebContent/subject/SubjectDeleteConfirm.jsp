<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>削除確認</title> <!-- ページタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- CSSファイルをリンク -->
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
