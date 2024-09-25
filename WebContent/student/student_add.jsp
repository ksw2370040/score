<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTLコアタブライブラリのインポート -->
<!DOCTYPE html>
<html lang="ja"> <!-- 言語を日本語に設定 -->
<head>
    <meta charset="UTF-8"> <!-- 文字エンコーディングをUTF-8に設定 -->
    <title>学生管理</title> <!-- ページのタイトル -->
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

            // 入学年度とクラスが未選択の場合にエラーメッセージを表示
            f1Error.style.display = f1Select.value === '0' ? 'block' : 'none';
            f2Error.style.display = f2Select.value === '0' ? 'block' : 'none';
            // 学生番号と氏名が未入力の場合にエラーメッセージを表示
            studentNoError.style.display = studentNo.value.trim() === '' ? 'block' : 'none';
            studentNameError.style.display = studentName.value.trim() === '' ? 'block' : 'none';

            // すべてのフィールドが正しく入力されている場合はtrueを返す
            return f1Select.value !== '0' && f2Select.value !== '0' && studentNo.value.trim() !== '' && studentName.value.trim() !== '';
        }

        // 既存の学生番号を配列として格納
        const existingCodes = [
            <c:forEach var="code" items="${existingSubjectCodes}"> // 既存の学生番号をJSPから取得
                "${code}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        console.log(existingCodes); // デバッグ用にコンソールに出力

        // 学生番号の重複チェックを行う関数
        function checkDuplicate() {
            const inputCode = document.getElementById("student-no").value; // 入力された学生番号

            // 既存の学生番号に含まれている場合、アラートを表示
            if (existingCodes.includes(inputCode)) {
                alert("この学生番号は既に存在します。");
                return false; // 重複があった場合はフォーム送信を停止
            }

            return true; // 重複がなければtrueを返す
        }
    </script>
</head>
<body>
<c:import url="../common/base.jsp"> <!-- 共通のベースJSPをインポート -->
    <c:param name="title"></c:param> <!-- タイトルパラメータ -->
    <c:param name="scripts"></c:param> <!-- スクリプトパラメータ -->
    <c:param name="content"> <!-- コンテンツパラメータの開始 -->
        <section class="me-4"> <!-- 学生情報登録セクション -->
            <h2>学生情報登録</h2> <!-- セクションタイトル -->
            <c:if test="${not empty error}"> <!-- エラーメッセージが存在する場合 -->
                <p style="color: red;">${error}</p> <!-- エラーメッセージを表示 -->
                <c:set var="error" value="${null}" scope="session" /> <!-- セッションのエラーをクリア -->
            </c:if>

            <form action="StudentCreateExecute.action" method="get" onsubmit="return validateForm()"> <!-- フォームの送信先とメソッド -->
                <div id="filter"> <!-- フィルタDIV -->
                    <div>
                        <label for="student-f1-create">入学年度</label> <!-- 入学年度ラベル -->
                        <select id="student-f1-create" name="ent_year"> <!-- 入学年度セレクトボックス -->
                            <option value="0">--------</option> <!-- デフォルトオプション -->
                            <c:forEach var="year" items="${ent_year_set}"> <!-- 入学年度のオプションをループ -->
                                <option value="${year}" <c:if test="${year==ent_year }">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                        <div id="f1-error" class="error-message" style="display:none; color:orange;">入学年度を選択してください。</div> <!-- 入学年度エラーメッセージ -->
                    </div>
                    <div>
                        <label for="student-no">学生番号</label> <!-- 学生番号ラベル -->
                        <input id="student-no" type="text" name="no" placeholder="学生番号を入力してください" maxlength="10" required> <!-- 学生番号入力フィールド -->
                        <div id="student-no-error" class="error-message" style="display:none;">学生番号を入力してください。</div> <!-- 学生番号エラーメッセージ -->
                    </div>
                    <div>
                        <label for="student-name">氏名</label> <!-- 氏名ラベル -->
                        <input id="student-name" type="text" name="name" placeholder="氏名を入力してください" maxlength="30" required> <!-- 氏名入力フィールド -->
                        <div id="student-name-error" class="error-message" style="display:none;">氏名を入力してください。</div> <!-- 氏名エラーメッセージ -->
                    </div>
                    <div>
                        <label for="student-f2-create">クラス</label> <!-- クラスラベル -->
                        <select id="student-f2-create" name="class_num"> <!-- クラスセレクトボックス -->
                            <option value="0">--------</option> <!-- デフォルトオプション -->
                            <c:forEach var="num" items="${class_num_set}"> <!-- クラスのオプションをループ -->
                                <option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                        <div id="f2-error" class="error-message" style="display:none;">選択してください。</div> <!-- クラスエラーメッセージ -->
                    </div>
                </div>
                <button id="filter-button">登録して終了</button> <!-- 登録ボタン -->
                <div>${errors.get("f1")}</div> <!-- フィールドエラーメッセージ -->
            </form>
            <p></p>
            <div>
                <a href="../student/StudentList.action">戻る</a> <!-- 学生一覧に戻るリンク -->
            </div>
        </section>
    </c:param> <!-- コンテンツパラメータの終了 -->
</c:import>
</body>
</html>
