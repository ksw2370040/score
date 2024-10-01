<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
    <link rel="stylesheet" type="text/css" href="../css/student.css"> <!-- スタイルシートのリンク -->
</head>
<body>
	<!-- 共通のベースJSPをインポート -->
    <c:import url="../common/base.jsp">
        <c:param name="title"></c:param>
        <c:param name="scripts"></c:param>
        <c:param name="content">
            <section class="me-4"> <!-- メインコンテンツセクション -->
                <h2>学生管理</h2> <!-- セクションタイトル -->
                <div class="new">
                    <a href="StudentCreate.action">新規登録</a> <!-- 新規登録へのリンク -->
                </div>
                <form method="get"> <!-- フィルタリング用のフォーム -->
                    <div id="filter">
                        <div class="form-group">
                            <label for="student-f1-select">入学年度</label>
                            <select id="student-f1-select" name="f1"> <!-- 入学年度の選択 -->
                                <option value="0">--------</option> <!-- デフォルトオプション -->
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year}</option> <!-- 年度のオプションを動的に生成 -->
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f2-select">クラス</label>
                            <select id="student-f2-select" name="f2"> <!-- クラスの選択 -->
                                <option value="0">--------</option> <!-- デフォルトオプション -->
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num==f2 }">selected</c:if>>${num}</option> <!-- クラス番号のオプションを動的に生成 -->
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="student-f3-check">在学中
                                <input type="checkbox" id="student-f3-check" name="f3" value="t" <c:if test="${!empty f3}">checked</c:if>> <!-- 在学中のチェックボックス -->
                            </label>
                        </div>
                        <div>
                            <button id="filter-button">絞込み</button> <!-- 絞込みボタン -->
                        </div>
                        <div style="color:red; ">${errors.get("f1")}</div> <!-- エラーメッセージ -->
                    </div>
                </form>

                <c:choose>
                    <c:when test="${students.size()>0}"> <!-- 学生情報が存在する場合 -->
                        <div>検索結果:${students.size()}件</div> <!-- 検索結果件数表示 -->
                        <table>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th class="center">在学中</th>
                                <th></th>
                            </tr>
                            <c:forEach var="student" items="${students}"> <!-- 学生情報のリストを表示 -->
                                <tr>
                                    <td>${student.entYear}</td> <!-- 入学年度 -->
                                    <td>${student.no}</td> <!-- 学生番号 -->
                                    <td>${student.name}</td> <!-- 氏名 -->
                                    <td>${student.classNum}</td> <!-- クラス番号 -->
                                    <td class="center">
                                        <c:choose>
                                            <c:when test="${student.isAttend()}"> <!-- 在学中かどうかをチェック -->
                                                〇
                                            </c:when>
                                            <c:otherwise>
                                                ×
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td> <!-- 学生情報変更へのリンク -->
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>学生情報が存在しませんでした</div> <!-- 学生情報が存在しない場合 -->
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
