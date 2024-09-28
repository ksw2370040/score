<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link href="../css/student-add.css" rel="stylesheet" /> <!-- スタイルシートのリンク -->
<style>
/* styles.css */

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
/* フォームグループのスタイル */
.form-group {
    display: flex;
    flex-direction: column; /* ラベルとセレクトボックスを縦に配置 */
}



#filter div {
    flex-direction: column; /* ラベルと入力要素を縦に配置 */
    margin-right: 20px; /* 各要素の間に余白を追加 */
}





#filter {
    display: flex;
    flex-direction: column;
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
#filter-button {
    padding: 10px 20px; /* ボタンのパディングを設定 */
    background-color: #bbbbbb; /* ボタンの背景色 */
    color: white; /* ボタンの文字色 */
    border: none; /* ボタンの境界線をなくす */
    border-radius: 5px; /* ボタンの角を丸くする */
    cursor: pointer; /* ホバー時のカーソルをポインターにする */
}


    .error-message {
    display: none ;
    color: red;
    font-size: 14px;
    margin-top: 5px;
}
.link-container {
    margin-top: 200px; /* 全体を下に移動 */
}

.link-container a {
    margin-right: 50px; /* リンク間の間隔 */
}

#addcomplete h2 {
    font-size: 24px;
    background-color: #bbbbbb; /* 背景色を設定 */
    padding: 10px 20px; /* タイトルの周りに余白を追加 */
}

#addcomplete p {
    background-color: #339966;
    text-align: center;
     height: 40px; /* 高さを変更 */
    line-height: 40px; /* 高さに合わせてテキストを中央に配置 */
    }

</style>
</head>
<body>
<!-- 共通のベースJSPをインポート -->
<c:import url="../common/base.jsp">
    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4"> <!-- 内容セクション -->
            <div id="addcomplete">
                <h2>学生情報登録</h2> <!-- セクションタイトル -->
                <p>登録が完了しました</p> <!-- 登録完了メッセージ -->
                <div class="link-container">
                    <a href="../student/StudentCreate.action">戻る</a> <!-- 戻るリンク -->
                    <a href="../student/StudentList.action">学生一覧</a> <!-- 学生一覧リンク -->
                </div>
            </div>
        </section>
    </c:param>
</c:import>
</body>
</html>
