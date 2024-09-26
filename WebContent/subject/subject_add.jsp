<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングを指定 -->
    <title>学生管理</title> <!-- ページタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css"> <!-- CSSファイルをリンク -->
    <script>
        // 既存の科目コードをJavaScriptの配列として取得
        const existingCodes = [
            <c:forEach var="code" items="${existingSubjectCodes}">
                "${code}"<c:if test="${!status.last}">,</c:if> <!-- 最後の要素でない場合はカンマを追加 -->
            </c:forEach>
        ];

        console.log(existingCodes); // 取得したコードをコンソールに表示

        // 入力された科目コードが既存のコードと重複しているかチェックする関数
        function checkDuplicate() {
            const inputCode = document.getElementById("radius").value; // 入力フィールドから値を取得

            // 既存のコードに重複があれば警告を表示
            if (existingCodes.includes(inputCode)) {
                alert("この科目コードは既に存在します。"); // 重複時の警告メッセージ
                return false; // フォーム送信を中止
            }

            return true; // 重複がなければフォームを送信
        }
    </script>
</head>
<body>
 <!-- 共通の基盤となるJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param> 
        <c:param name="content"> 
            <section class="me-4"> <!-- コンテンツセクション -->
                <h2>科目情報登録</h2> <!-- セクション見出し -->
                <c:if test="${not empty error}"> <!-- エラーメッセージの表示 -->
                    <p style="color: red;">${error}</p> <!-- エラーメッセージ -->
                    <c:set var="error" value="${null}" scope="session" /> <!-- セッション内のエラーメッセージをクリア -->
                </c:if>
                <form action="SubjectCreateExecute.action" method="post" onsubmit="return checkDuplicate()"> <!-- フォーム開始 -->
                    <div id="filter"> <!-- フィルタコンテナ -->
                        <div>
                            <label>科目コード</label> <!-- 科目コードのラベル -->
                            <input id="radius" type="text" name="cd" placeholder="科目コードを入力してください" maxlength="3" pattern=".{3}" required title="3文字を入力してください"><br>
                            <!-- 科目コードの入力フィールド -->
                        </div>
                        <div>
                            <label>科目名</label> <!-- 科目名のラベル -->
                            <input id="radius" type="text" name="name" placeholder="科目名を入力してください" required><br>
                            <!-- 科目名の入力フィールド -->
                        </div>
                        <div>
                            <button id="button">登録</button> <!-- 登録ボタン -->
                        </div>
                    </div>
                </form>
                <div>
                    <a href="../subject/SubjectList.action">戻る</a> <!-- 科目一覧ページへのリンク -->
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
