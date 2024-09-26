<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/result.css"> <!-- スタイルシートの読み込み -->

    <script>
        // 情報メッセージを非表示にする関数
        function hideInfoMessage() {
            var infoMessage = document.getElementById('info-message');
            infoMessage.style.display = 'none'; // メッセージを非表示にする

            var inputField = document.getElementById('subject-name');
            var errorMessage = document.getElementById('error-message');

            // 入力フィールドが空の場合、エラーメッセージを表示
            if (inputField.value.trim() === '') {
                errorMessage.style.display = 'block'; // エラーメッセージを表示
                return false; // フォームの送信を停止
            } else {
                errorMessage.style.display = 'none'; // エラーメッセージを非表示
                return true; // フォームの送信を続行
            }
        }

        // フォームのバリデーションを行う関数
        function validateForm() {
            var f1Select = document.getElementById('student-f1-select'); // 入学年度のセレクトボックス
            var f2Select = document.getElementById('student-f2-select'); // クラスのセレクトボックス
            var selectionError = document.getElementById('selection-error'); // エラーメッセージの表示エリア

            // 初期メッセージを非表示にする
            hideInfoMessage();

            // 入学年度またはクラスが未選択の場合、エラーメッセージを表示
            if (f1Select.value === '0' || f2Select.value === '0') {
                selectionError.style.display = 'block'; // エラーメッセージを表示
                return false; // フォームの送信を停止
            } else {
                selectionError.style.display = 'none'; // エラーメッセージを非表示
                return true; // フォームの送信を続行
            }
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
                <h2>成績参照 <c:if test="${TLsubs.size() > 0}">（科目）</c:if></h2>
                <div id="box">
                    <!-- 科目情報を選択するフォーム -->
                    <form method="get" onsubmit="hideInfoMessage(); return validateForm();">
                        <div id="filter">
                            <div class="label-group">
                                <p>科目情報</p>
                            </div>
                            <div class="form-group">
                                <label for="student-f1-select">入学年度</label>
                                <select id="student-f1-select" name="f1">
                                    <option value="0">--------</option>
                                    <!-- 入学年度のオプションを動的に生成 -->
                                    <c:forEach var="year" items="${ent_year_set}">
                                        <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="student-f2-select">クラス</label>
                                <select id="student-f2-select" name="f2">
                                    <option value="0">--------</option>
                                    <!-- クラスのオプションを動的に生成 -->
                                    <c:forEach var="num" items="${class_num_set}">
                                        <option value="${num}" <c:if test="${num==f2 }">selected</c:if>>${num}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="student-f3-select">科目名</label>
                                <select id="student-f3-select" name="f3">
                                    <option value="0">--------</option>
                                    <!-- 科目名のオプションを動的に生成 -->
                                    <c:forEach var="sub" items="${subs}">
                                        <option value="${sub.cd}" <c:if test="${sub.cd==f3 }">selected</c:if>>${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <button id="filter-button" type="submit">検索</button> <!-- 検索ボタン -->
                            </div>
                        </div>
                        <!-- フィルタが設定されている場合のエラーメッセージ -->
                        <c:if test="${not empty param.f1 or not empty param.f2 or not empty param.f3}">
                            <div id="selection-error" class="error-message" style="color:orange;">${errors.get("f1")}</div>
                        </c:if>
                    </form>
                    <hr>

                    <!-- 学生番号を検索するフォーム -->
                    <form method="get" onsubmit="hideInfoMessage();">
                        <div id="filter2">
                            <div class="form-group2">
                                <p>学生情報</p>
                            </div>
                            <div class="form-group2">
                                <label for="student-f1-select2">学生番号</label>
                                <input id="subject-name" type="text" name="f4" placeholder="学生番号を入力してください" required> <!-- 学生番号入力フィールド -->
                            </div>

                            <div>
                                <button id="filter-button2" type="submit">検索</button> <!-- 検索ボタン -->
                            </div>
                        </div>
                    </form>
                </div>

                <!-- 学生番号が入力されている場合に氏名を表示 -->
                <c:if test="${not empty f4}">
                    <c:if test="${stu.no == f4}">
                        <div>氏名: ${stu.name}(${stu.no})</div> <!-- 学生の氏名と番号を表示 -->
                    </c:if>
                </c:if>

                <c:choose>
                    <c:when test="${TLsubs.size() > 0}">
                        <c:forEach var="sub" items="${subs}">
                            <c:if test="${sub.cd == f3}">
                                <div>科目: ${sub.name}</div> <!-- 選択された科目名を表示 -->
                            </c:if>
                        </c:forEach>

                        <!-- 科目情報がある場合の表示 -->
                        <div class="subject-table">
                            <table>
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>１回目</th>
                                    <th>２回目</th>
                                </tr>
                                <c:forEach var="TLsub" items="${TLsubs}">
                                    <tr>
                                        <td>${TLsub.entYear}</td> <!-- 入学年度 -->
                                        <td>${TLsub.classNum}</td> <!-- クラス番号 -->
                                        <td>${TLsub.studentNo}</td> <!-- 学生番号 -->
                                        <td>${TLsub.studentName}</td> <!-- 学生名 -->
                                        <td>
                                            <c:forEach var="entry" items="${TLsub.points}">
                                                <c:if test="${entry.key == 1}">
                                                    <c:choose>
                                                        <c:when test="${entry.value == 0}">
                                                            <c:out value="-"/> <!-- 点数が0の場合 -->
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${entry.value}"/> <!-- 点数を表示 -->
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="entry" items="${TLsub.points}">
                                                <c:if test="${entry.key == 2}">
                                                    <c:choose>
                                                        <c:when test="${entry.value == 0}">
                                                            <c:out value="-"/> <!-- 点数が0の場合 -->
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${entry.value}"/> <!-- 点数を表示 -->
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:when>
                    <c:when test="${TLstus.size() > 0}">
                        <!-- 学生情報がある場合の表示 -->
                        <div class="subject-table2">
                            <table>
                                <tr>
                                    <th>科目名</th>
                                    <th>科目コード</th>
                                    <th>回数</th>
                                    <th>点数</th>
                                </tr>
                                <c:forEach var="TLstu" items="${TLstus}">
                                    <tr>
                                        <td>${TLstu.subjectName}</td> <!-- 科目名 -->
                                        <td>${TLstu.subjectCd}</td> <!-- 科目コード -->
                                        <td>${TLstu.num}</td> <!-- 回数 -->
                                        <td>${TLstu.point}</td> <!-- 点数 -->
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:when>
                    <c:when test="${not empty f4 }">
                        <c:if test="${TLstus.size() == 0 }">
                            <div id="no-data-message" class="info-message2">
                                <p>学生情報は存在しませんでした</p> <!-- 学生情報が存在しない場合のメッセージ -->
                            </div>
                        </c:if>
                    </c:when>
                    <c:when test="${not empty f1 and f1 > '0' and not empty f2 and f2 > '0' and not empty f3 and f3 > '0'}">
                        <c:if test="${TLstus.size() == 0 }">
                            <div id="no-data-message" class="info-message2">
                                <p>学生情報は存在しませんでした</p> <!-- 学生情報が存在しない場合のメッセージ -->
                            </div>
                        </c:if>
                    </c:when>

                    <c:otherwise>
                        <!-- メッセージの表示条件を追加 -->
                        <c:if test="${not empty nosearch}">
                            <div id="no-data-message" class="info-message">
                                <p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p> <!-- 検索を促すメッセージ -->
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
