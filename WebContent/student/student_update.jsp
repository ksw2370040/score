<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8"> <!-- ページの文字エンコーディング -->
    <title>学生管理</title> <!-- ページのタイトル -->
    <link rel="stylesheet" type="text/css" href="../css/student-add.css"> <!-- スタイルシートのリンク -->
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
