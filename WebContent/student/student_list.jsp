<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/student.css">

</head>
<body>
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4">
                <h2>学生管理</h2>
                <div class="new">
                    <a href="StudentCreate.action">新規登録</a>
                </div>
                <form method="get">
                    <div id="filter">
                        <div class="form-group">
                            <label for="student-f1-select">入学年度</label>
                            <select id="student-f1-select" name="f1">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f2-select">クラス</label>
                            <select id="student-f2-select" name="f2">
                                <option value="0">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num==f2 }">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f3-check">在学中
                                <input type="checkbox" id="student-f3-check" name="f3" value="t" <c:if test="${!empty f3}">checked</c:if>>
                            </label>
                        </div>
                        <div>
                            <button id="filter-button">絞込み</button>
                        </div>
                        <div style="color:red; ">${errors.get("f1")}</div>
                    </div>
                </form>
                <c:choose>
                    <c:when test="${students.size()>0}">
                        <div>検索結果:${students.size()}件</div>
                        <table>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th class="center">在学中</th>
                                <th></th>
                            </tr>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
                                    <td>${student.classNum}</td>
                                    <td class="center">
                                        <c:choose>
                                            <c:when test="${student.isAttend()}">
                                                〇
                                            </c:when>
                                            <c:otherwise>
                                                ×
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>学生情報が存在しませんでした</div>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
