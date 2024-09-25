<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
    <title>学生管理</title> <!-- ページタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- スタイルシートのリンク -->
    <script>
        // 既存の科目コードを配列に格納
        const existingCodes = [
            <c:forEach var="code" items="${existingSubjectCodes}"> <!-- 既存の科目コードをループで取得 -->
                "${code}"<c:if test="${!status.last}">,</c:if> <!-- 最後の要素以外にはカンマを追加 -->
            </c:forEach>
        ];

        console.log(existingCodes); // 既存の科目コードをコンソールに表示

        // 重複チェックの関数
        function checkDuplicate() {
            const inputCode = document.getElementById("radius").value; // 入力された科目コードを取得

            // 既存のコードと重複しているかチェック
            if (existingCodes.includes(inputCode)) {
                alert("この科目コードは既に存在します。"); // 重複があればアラートを表示
                return false; // フォーム送信を中止
            }

            return true; // 重複がなければ送信を続行
        }
    </script>
</head>
<body>
    <c:import url="../common/base.jsp"> <!-- 共通のベースページをインポート -->
        <c:param name="title"></c:param> <!-- ページタイトルを設定 -->
        <c:param name="scripts"></c:param> <!-- スクリプトを設定 -->
        <c:param name="content"> <!-- コンテンツ部分を定義 -->
            <section class="me-4">
                <h2>科目情報登録</h2> <!-- 見出し -->
                <c:if test="${not empty error}"> <!-- エラーメッセージが存在する場合 -->
                    <p style="color: red;">${error}</p> <!-- エラーメッセージを赤色で表示 -->
                    <c:set var="error" value="${null}" scope="session" /> <!-- セッションのエラーをクリア -->
                </c:if>
                <form action="SubjectCreateExecute.action" method="post" onsubmit="return checkDuplicate()"> <!-- フォーム送信時に重複チェックを実行 -->
                    <div id="filter">
                        <div>
                            <label>科目コード</label> <!-- 科目コードのラベル -->
                            <input id="radius" type="text" name="cd" placeholder="科目コードを入力してください" maxlength="3" pattern=".{3}" required title="3文字を入力してください"><br> <!-- 科目コードの入力フィールド -->
                        </div>
                        <div>
                            <label>科目名</label> <!-- 科目名のラベル -->
                            <input id="radius" type="text" name="name" placeholder="科目名を入力してください" required><br> <!-- 科目名の入力フィールド -->
                        </div>
                        <div>
                            <button id="button">登録</button> <!-- 登録ボタン -->
                        </div>
                    </div>
                </form>
                <div>
                    <a href="../subject/SubjectList.action">戻る</a> <!-- 戻るリンク -->
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
