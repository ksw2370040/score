<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/student-add.css"> <!-- スタイルシートのリンク -->
    <script>
        // フォームのバリデーションを行う関数
        function validateForm() {
            var f1Select = document.getElementById('student-f1-create'); // 入学年度セレクトボックス
            var f2Select = document.getElementById('student-f2-create'); // クラスセレクトボックス
            var f1Error = document.getElementById('f1-error'); // 入学年度エラーメッセージ
            var f2Error = document.getElementById('f2-error'); // クラスエラーメッセージ
            var studentNo = document.getElementById('student-no'); // 学生番号入力フィールド
            var studentName = document.getElementById('student-name'); // 氏名入力フィールド
            var studentNoError = document.getElementById('student-no-error'); // 学生番号エラーメッセージ
            var studentNameError = document.getElementById('student-name-error'); // 氏名エラーメッセージ

            // 各フィールドのバリデーションチェック
            f1Error.style.display = f1Select.value === '0' ? 'block' : 'none';
            f2Error.style.display = f2Select.value === '0' ? 'block' : 'none';
            studentNoError.style.display = studentNo.value.trim() === '' ? 'block' : 'none';
            studentNameError.style.display = studentName.value.trim() === '' ? 'block' : 'none';

            // すべてのフィールドが正しい場合にtrueを返す
            return f1Select.value !== '0' && f2Select.value !== '0' && studentNo.value.trim() !== '' && studentName.value.trim() !== '';
        }

        // 既存の学生番号を配列に格納
        const existingCodes = [
            <c:forEach var="code" items="${existingSubjectCodes}">
                "${code}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        console.log(existingCodes); // デバッグ用：既存の学生番号をコンソールに表示

        // 学生番号の重複チェックを行う関数
        function checkDuplicate() {
            const inputCode = document.getElementById("student-no").value; // 入力された学生番号

            if (existingCodes.includes(inputCode)) { // 既存の学生番号と重複するか確認
                alert("この学生番号は既に存在します。"); // 重複時のアラート
                return false;
            }

            return true; // 重複がなければtrueを返す
        }
    </script>
</head>
<body>
<!-- 共通のベースJSPをインポート -->
<c:import url="../common/base.jsp">
    <c:param name="title"></c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2>学生情報登録</h2> <!-- セクションタイトル -->
            <c:if test="${not empty error}"> <!-- エラーが存在する場合 -->
                <p style="color: red;">${error}</p> <!-- エラーメッセージを表示 -->
                <c:set var="error" value="${null}" scope="session" /> <!-- セッションのエラーをリセット -->
            </c:if>

            <form action="StudentCreateExecute.action" method="get" onsubmit="return validateForm()"> <!-- フォーム送信時にバリデーション -->
                <div id="filter">
                    <div>
                        <label for="student-f1-create">入学年度</label>
                        <select id="student-f1-create" name="ent_year"> <!-- 入学年度選択 -->
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==ent_year }">selected</c:if>>${year}</option> <!-- 年度のオプションを動的に生成 -->
                            </c:forEach>
                        </select>
                        <div id="f1-error" class="error-message" style="display:none; color:orange;">入学年度を選択してください。</div> <!-- エラーメッセージ -->
                    </div>
                    <div>
                        <label for="student-no">学生番号</label>
                        <input id="student-no" type="text" name="no" placeholder="学生番号を入力してください" maxlength="10" required> <!-- 学生番号入力フィールド -->
                        <div id="student-no-error" class="error-message" style="display:none;">学生番号を入力してください。</div> <!-- エラーメッセージ -->
                    </div>
                    <div>
                        <label for="student-name">氏名</label>
                        <input id="student-name" type="text" name="name" placeholder="氏名を入力してください" maxlength="30" required> <!-- 氏名入力フィールド -->
                        <div id="student-name-error" class="error-message" style="display:none;">氏名を入力してください。</div> <!-- エラーメッセージ -->
                    </div>
                    <div>
                        <label for="student-f2-create">クラス</label>
                        <select id="student-f2-create" name="class_num"> <!-- クラス選択 -->
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option> <!-- クラス番号のオプションを動的に生成 -->
                            </c:forEach>
                        </select>
                        <div id="f2-error" class="error-message" style="display:none;">選択してください。</div> <!-- エラーメッセージ -->
                    </div>
                </div>
                <button id="filter-button">登録して終了</button> <!-- 登録ボタン -->
                <div>${errors.get("f1")}</div> <!-- エラー表示 -->
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
