<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <!-- CSSスタイルシートをリンク -->
    <link rel="stylesheet" type="text/css" href="../css/student-add.css">
    <script>
        // ボタンがクリックされたときのイベントリスナーを設定
        document.getElementById("changeButton").addEventListener("click", function() {
            // 他のページに移動するURLを指定
            window.location.href = "student_update_complete.jsp";
        });
    </script>
</head>
<body>
    <!-- 共通のベースレイアウトをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param> <!-- ページタイトルを設定 -->
        <c:param name="scripts"></c:param> <!-- 必要なスクリプトを設定 -->
        <c:param name="content">
            <section class="me-4">
                <!-- 学生情報変更フォーム -->
                <form action="StudentUpdateExecute.action" method="get">
                    <div id="filter">
                        <h2>学生情報変更</h2>
                        <div>
                            <label>入学年度</label>
                            <!-- 入学年度を表示 -->
                            <p>${student.entYear}</p>
                            <!-- 入学年度を隠しフィールドとして送信 -->
                            <input id="radius" type="hidden" name="ent_year" value="${student.entYear}" required>
                        </div>
                        <div>
                            <label>学生番号</label>
                            <!-- 学生番号を表示 -->
                            <p>${student.no}</p>
                            <!-- 学生番号を隠しフィールドとして送信 -->
                            <input id="radius" type="hidden" name="no" value="${student.no}" required>
                        </div>
                        <div>
                            <label>氏名</label>
                            <!-- 氏名を入力するフィールド -->
                            <input id="radius" type="text" name="name" value="${student.name}" required><br>
                        </div>
                        <div>
                            <label>クラス</label>
                            <select name="class_num">
                                <!-- 現在のクラスを表示 -->
                                <option value="0">${student.classNum}</option>
                                <!-- クラスの選択肢を生成 -->
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label>在学中
                                <!-- 在学中かどうかを選択するチェックボックス -->
                                <input type="checkbox" name="isAttend" value="t"
                                <c:if test="${!empty isAttend}">checked</c:if>>
                            </label>
                        </div>
                        <div>
                            <!-- 変更ボタン -->
                            <button id="changeButton">変更</button>
                        </div>
                        <!-- エラーメッセージを表示 -->
                        <div>${errors.get("f1")}</div>
                    </div>
                </form>
                <p></p>
                <div>
                    <!-- 学生一覧へのリンク -->
                    <a href="../student/StudentList.action">戻る</a>
                </div>
            </section>
        </c:param>
    </c:import>
</body>
</html>
