<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- ページの文字エンコーディング -->
    <title>学生管理</title> <!-- ページのタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/student-add.css"> <!-- スタイルシートのリンク -->
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
    <script>
        // ボタンがクリックされたときのイベントリスナー
        document.getElementById("changeButton").addEventListener("click", function() {
            // 他のページに移動するURLを指定
            window.location.href = "student_update_complete.jsp"; // 完了ページに遷移
        });
    </script>
</head>
<body>
<!-- 共通のベースJSPをインポート -->
<c:import url="../common/base.jsp">
    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4"> <!-- メインコンテンツセクション -->
            <form action="StudentUpdateExecute.action" method="get"> <!-- フォームの開始 -->
                <div id="filter">
                    <h2>学生情報変更</h2> <!-- セクションタイトル -->
                    <div>
                        <label>入学年度</label> <!-- 入学年度のラベル -->
                        <p>${student.entYear}</p> <!-- 入学年度の表示 -->
                        <input id="radius" type="hidden" name="ent_year" value="${student.entYear}" required> <!-- 入学年度の隠しフィールド -->
                    </div>
                    <div>
                        <label>学生番号</label> <!-- 学生番号のラベル -->
                        <p>${student.no}</p> <!-- 学生番号の表示 -->
                        <input id="radius" type="hidden" name="no" value="${student.no}" required> <!-- 学生番号の隠しフィールド -->
                    </div>
                    <div>
                        <label>氏名</label> <!-- 氏名のラベル -->
                        <input id="radius" type="text" name="name" value="${student.name}" required><br> <!-- 氏名の入力フィールド -->
                    </div>
                    <div>
                        <label>クラス</label> <!-- クラスのラベル -->
                        <select name="class_num"> <!-- クラス選択用のドロップダウン -->
                            <option value="0">${student.classNum}</option> <!-- 現在のクラスを表示 -->
                            <c:forEach var="num" items="${class_num_set}"> <!-- クラスの選択肢をループで生成 -->
                                <option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option> <!-- 選択中のクラスをハイライト -->
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label>在学中
                            <input type="checkbox" name="isAttend" value="t"
                            <c:if test="${!empty isAttend}">checked</c:if>></label> <!-- チェックボックスの状態を設定 -->
                    </div>
                    <div>
                        <button id="changeButton">変更</button> <!-- 変更ボタン -->
                    </div>
                    <div>${errors.get("f1")}</div> <!-- エラーメッセージを表示 -->
                </div>
            </form>
            <p></p>
            <div>
                <a href="../student/StudentList.action">戻る</a> <!-- 戻るリンク -->
            </div>
        </section>
    </c:param>
</c:import>
</body>
</html>
