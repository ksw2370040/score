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
			<c:choose>
			    <c:when test="${tests.size() >= 0}">
					<form action="TestDeleteExecute.action" method="get">
			            <table>
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
			            <p>本当に削除しますか</p>
			            <button>削除する</button>
			        </form>
			    </c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>
</body>
</html>

