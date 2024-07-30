<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <!-- 文字エンコーディングを設定 -->
    <meta charset="UTF-8">
    <!-- ページタイトルを設定 -->
    <title>学生管理</title>
    <!-- CSSスタイルシートをリンク -->
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
    <script>
        // 既存の科目コードを JavaScript の配列に設定
        const existingCodes = [
            <c:forEach var="code" items="${existingSubjectCodes}">
                "${code}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        console.log(existingCodes);

        // 科目コードの重複をチェックする関数
        function checkDuplicate() {
            const inputCode = document.getElementById("radius").value;

            if (existingCodes.includes(inputCode)) {
                alert("この科目コードは既に存在します。");
                return false; // フォームの送信を中止
            }

            return true; // フォームの送信を続行
        }
    </script>
</head>
<body>
    <!-- 共通のベースレイアウトをインポート -->
    <c:import url="../common/base.jsp">
        <!-- ページタイトルの設定 -->
        <c:param name="title"></c:param>
        <!-- 必要なスクリプトの設定 -->
        <c:param name="scripts"></c:param>
        <!-- ページコンテンツの設定 -->
        <c:param name="content">
            <section class="me-4">
                <!-- 見出し -->
                <h2>科目情報登録</h2>
                <!-- エラーメッセージの表示 -->
                <c:if test="${not empty error}">
                    <p style="color: red;">${error}</p>
                    <c:set var="error" value="${null}" scope="session" />
                </c:if>
                <!-- 科目情報登録フォーム -->
                <form action="SubjectCreateExecute.action" method="post" onsubmit="return checkDuplicate()">
                    <div id="filter">
                        <div>
                            <label>科目コード</label>
                            <!-- 科目コードの入力欄 -->
                            <input id="radius" type="text" name="cd" placeholder="科目コードを入力してください" maxlength="3" pattern=".{3}" required title="3文字を入力してください"><br>
                        </div>
                        <div>
                            <label>科目名</label>
                            <!-- 科目名の入力欄 -->
                            <input id="radius" type="text" name="name" placeholder="科目名を入力してください" required><br>
                        </div>
                        <div>
                            <!-- 登録ボタン -->
                            <button id="button">登録</button>
                        </div>
                    </div>
                </form>
                <!-- 戻るリンク -->
                <div>
                    <a href="../subject/SubjectList.action">戻る</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
