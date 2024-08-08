<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang = "ja">
<head>
	<meta charset="UTF-8">
    <title>成績登録</title>
    <link rel="stylesheet" type="text/css" href="../css/results.css">
</head>
<body>
<c:import url="../common/base.jsp">
	<c:param name="title"></c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="me-4">
			<h2>成績管理</h2>

			<form method="get">
				<div id="filter">
					<div id="form-group">
						<label for="test-f1-select">入学年度</label>
						<select id="test-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div id="form-group">
						<label for="test-f2-select">クラス</label>
						<select id="test-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div id="form-group">
						<label for="test-f3-select">科目名</label>
						<select id="test-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="sub" items="${subjects}">
								<option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>${sub.name}</option>
							</c:forEach>
						</select>
					</div>

					<div id="form-group">
						<label for="test-f4-select">回数</label>
						<select id="test-f4-select" name="f4">
							<option value="0">0</option>
							<c:forEach var="n" items="${number}">
								<option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
							</c:forEach>
						</select>
					</div>

					<div>
						<button id="filter-button" type="submit">検索</button>
					</div>

					<div>${errors.get("f1")}</div>
				</div>
			</form>

			<c:choose>
			    <c:when test="${tests.size() > 0}">
					<form action="TestRegist2Execute.action" method="get">
			            <c:set var="test" value="${tests[0]}" />
    					<div>科目:${subject.name} (${test.no}回)</div>

			            <table>
			                <tr>
			                    <th>入学年度</th>
			                    <th>クラス</th>
			                    <th>学生番号</th>
			                    <th>氏名</th>
			                    <th>点数</th>
			                </tr>
							<c:forEach var="test" items="${tests}">
			                    <tr>
			                        <td>${test.student.entYear}</td>
			                        <td>${test.student.classNum}</td>
			                        <td>${test.student.no}</td>
			                        <td>${test.student.name}</td>
			                        <td>
										<input id="text" type="text" name="test_point" value="${test.point}" required>
									</td>
									<td><input type="hidden" name="student_no" value="${test.student.no}" required></td>
									<td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td>
									<td><input type="hidden" name="class_num" value="${test.classNum}" required></td>
									<td><input type="hidden" name="test_no" value="${test.no}" required></td>
									<td><input type="hidden" name="count" value="${tests.size()}" required></td>
								</tr>
							</c:forEach>
						</table>
						<button id="filter-button" type="submit">登録して終了</button>
					</form>
			    </c:when>
			    <c:otherwise>
			        <div>テストデータが見つかりませんでした。</div>
			    </c:otherwise>
			</c:choose><br><br>

			<c:choose>
			    <c:when test="${tests.size() > 0}">
					<form action="TestDelete.action" method="get">
			            <table class="table2">
							<c:forEach var="test" items="${tests}">
			                    <tr>
									<td><input type="hidden" name="student_no" value="${test.student.no}" required></td>
									<td><input type="hidden" name="subject_cd" value="${test.subject.cd}" required></td>
									<td><input type="hidden" name="class_num" value="${test.classNum}" required></td>
									<td><input type="hidden" name="test_no" value="${test.no}" required></td>
									<td><input type="hidden" name="count" value="${tests.size()}" required></td>
								</tr>
							</c:forEach>
			            </table>
			            <button id="filter-button" type="submit">削除する</button>
			        </form>
			    </c:when>
			</c:choose>

		</section>
	</c:param>
</c:import>
</body>
</html>

