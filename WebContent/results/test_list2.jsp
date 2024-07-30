<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/result.css">
    <script>
        function hideInfoMessage() {
            var infoMessage = document.getElementById('info-message');
            infoMessage.style.display = 'none';

            var inputField = document.getElementById('subject-name');
            var errorMessage = document.getElementById('error-message');

            if (inputField.value.trim() === '') {
                errorMessage.style.display = 'block';
                return false; // フォームの送信を停止
            } else {
                errorMessage.style.display = 'none';
                return true; // フォームの送信を続行
            }

        }

        function validateForm() {
            var f1Select = document.getElementById('student-f1-select');
            var f2Select = document.getElementById('student-f2-select');
            var selectionError = document.getElementById('selection-error');

            // 初期メッセージを非表示にする
            hideInfoMessage();

            if (f1Select.value === '0' || f2Select.value === '0') {
                selectionError.style.display = 'block';
                return false;
            } else {
                selectionError.style.display = 'none';
                return true;
            }
        }
    </script>
</head>
<body>
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4">
                <h2>成績参照 <c:if test="${TLsubs.size() > 0}">（科目）</c:if></h2>
                <div id="box">
                    <form method="get" onsubmit="hideInfoMessage(); return validateForm();">
                        <div id="filter">
                            <div class="label-group">
                                <p>科目情報</p>
                            </div>
                            <div class="form-group">
                                <label for="student-f1-select">入学年度</label>
                                <select id="student-f1-select" name="f1">
                                    <option value="0">--------</option>
                                    <c:forEach var="year" items="${ent_year_set}">
                                        <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="student-f2-select">クラス</label>
                                <select id="student-f2-select" name="f2">
                                    <option value="0">--------</option>
                                    <c:forEach var="num" items="${class_num_set}">
                                        <option value="${num}" <c:if test="${num==f2 }">selected</c:if>>${num}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="student-f3-select">科目名</label>
                                <select id="student-f3-select" name="f3">
                                    <option value="0">--------</option>
                                    <c:forEach var="sub" items="${subs}">
                                        <option value="${sub.cd}" <c:if test="${sub.cd==f3 }">selected</c:if>>${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <button id="filter-button" type="submit">検索</button>
                            </div>
                        </div>
                        <c:if test="${not empty param.f1 or not empty param.f2 or not empty param.f3}">
                            <div id="selection-error" class="error-message" style=" color:orange;">${errors.get("f1")}</div>
                        </c:if>
                    </form>
                    <hr>

                    <form method="get" onsubmit="hideInfoMessage();">
                        <div id="filter2">
                            <div class="form-group2">
                                <p>学生情報</p>
                            </div>
                            <div class="form-group2">
                                <label for="student-f1-select2">学生番号</label>
                                <input id="subject-name" type="text" name="f4" placeholder="学生番号を入力してください" required>
                            </div>

                            <div>
                                <button id="filter-button2" type="submit">検索</button>
                            </div>
                        </div>
                    </form>
                </div>

				<c:if test="${not empty f4}">
						    <c:if test="${stu.no == f4}">
                        		<div>氏名: ${stu.name}(${stu.no})</div>
                            </c:if>
				</c:if>

                <c:choose>
                    <c:when test="${TLsubs.size() > 0}">
                        <c:forEach var="sub" items="${subs}">
                            <c:if test="${sub.cd == f3}">
                                <div>科目: ${sub.name}</div>
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
                                        <td>${TLsub.entYear}</td>
                                        <td>${TLsub.classNum}</td>
                                        <td>${TLsub.studentNo}</td>
                                        <td>${TLsub.studentName}</td>
                                        <td>
                                            <c:forEach var="entry" items="${TLsub.points}">
                                                <c:if test="${entry.key == 1}">
                                                    <c:choose>
                                                        <c:when test="${entry.value == 0}">
                                                            <c:out value="-"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${entry.value}"/>
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
                                                            <c:out value="-"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${entry.value}"/>
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
                                        <td>${TLstu.subjectName}</td>
                                        <td>${TLstu.subjectCd}</td>
                                        <td>${TLstu.num}</td>
                                        <td>${TLstu.point}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:when>
                        <c:when test="${not empty f4 }">
                        	<c:if test="${TLstus.size() == 0 }">
                            <div id="no-data-message" class="info-message2">
                                <p>学生情報は存在しませんでした</p>
                            </div>
                            </c:if>
                        </c:when>
					    <c:when test="${not empty f1 and f1 > '0' and not empty f2 and f2 > '0' and not empty f3 and f3 > '0'}">
					        <c:if test="${TLstus.size() == 0 }">
					            <div id="no-data-message" class="info-message2">
					                <p>学生情報は存在しませんでした</p>
					            </div>
					        </c:if>
					    </c:when>

                    <c:otherwise>

                        <!-- メッセージの表示条件を追加 -->
                        <c:if test="${not empty nosearch}">
                            <div id="no-data-message" class="info-message">
                                <p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
